import java.io.*;

/*
 * Codop Read 0
 * Codop Print 1
 * Codop Assign 2
 * 
*/

public class Translator {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    SymbolTable st = new SymbolTable();
    CodeGenerator code = new CodeGenerator();
    int count=0;
    public Translator(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }
    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
	    throw new Error("near line " + Lexer.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) move();
        } else error("syntax error" + (char) look.tag);
    }

    /*GUIDE:
        Guida(prog -> <statlist>EOF) = {assign,print,read,while,conditional,'{'}
    */
    public void prog(){
        switch(look.tag){
            case Tag.ASSIGN:
            case Tag.PRINT:
            case Tag.READ:
            case Tag.WHILE:
            case Tag.COND:
            case '{':
                statlist();
                match(Tag.EOF);
                try {
                    code.toJasmin();
                }
                catch(java.io.IOException e) {
                    System.out.println("IO error\n");
                };
            break;
            
            default:
                error("Error in prog");
            break;
        }
    }

    /*GUIDE:
        Guida(statlistp -> <stat><statlistp>) = {assign,print,read,while,conditional,'{'}
    */
    private void statlist(){
        switch(look.tag){
            case Tag.ASSIGN:
            case Tag.PRINT:
            case Tag.READ:
            case Tag.WHILE:
            case Tag.COND :
            case '{':
            stat();
            statlistp();
            break;
            
            default:
                error("Error in statlist");
            break;
        }
    }

    /*GUIDE:
    Guida(statlistp -> ;<stat><statlistp>) = {;}
    Guida(statlistp -> NULL) = {EOF, '}'}
    */
    private void statlistp(){
        switch(look.tag){
            case ';':
                match(';');
                stat();
                statlistp();
            return;

            case Tag.EOF:
            case '}':
            break;

            default:
                error("Error in statlistp");
            return;
        }
        

    }

    /*GUIDE:
    Guida(stat -> read[<idlist>]) = {read}
    Guida(stat -> print[<exprlist>]) = {print}
    Guida(stat -> assign<expr>to<idlist>) = {assign}
    Guida(stat -> while(<bexpr>)<stat>) = {while}
    Guida(stat -> conditional[<optlist>]<statp>) = {conditional}
    Guida(stat -> {<statlist>}) = {'{'}
    */
    private void stat(){
        switch(look.tag){

            //Codop Read 0
            case Tag.READ:
                match(Tag.READ);
                match('[');
                idlist(0,0);
                match(']');
            return;

            //Codop Print 1
            case Tag.PRINT:
                match(Tag.PRINT);
                match('[');
                exprlist(1);
                match(']');
            return;

            //Codop Assign 2
            case Tag.ASSIGN:
                match(Tag.ASSIGN);
                int num = expr();
                match(Tag.TO);
                idlist(2, num);
            return; 

            //Codop Per exp booleane normali 3
            case Tag.WHILE:
                match(Tag.WHILE);
                int label_while = code.newLabel();
                int label_end = code.newLabel();
                code.emitLabel(label_while);
                match('(');
                bexpr(3, label_end);
                match(')');
                stat();
                code.emit(OpCode.GOto, label_while);
                code.emitLabel(label_end);
            return;
            
            //Codop Per exp booleane normali 3
            case Tag.COND:
                int labelEndOpt = code.newLabel();
                match(Tag.COND);
                match('[');
                optlist(labelEndOpt);
                match(']');
                statp();
                code.emitLabel(labelEndOpt);
            return;

            case '{':
                match('{');
                statlist();
                match('}');
            return;

            default:
                error("Error in stat");
            break;
        }
    }

    /*GUIDE:
    Guida(statp -> end) = {end}
    Guida(statp -> else<stat>end) = {else}
    */
    private void statp(){
        switch(look.tag){
            case Tag.END:
                match(Tag.END);
            break;
            
            case Tag.ELSE:
                match(Tag.ELSE);
                stat();
                match(Tag.END);
            break;

            default:
                error("Error in statp");
            break;
        }
    }

    /*GUIDE:
    Guida(idlist -> ID<idlistp>) = {ID}
    */
    private void idlist(int codop, int num){
        if(look.tag == Tag.ID){
            int IdAddress = st.lookupAddress(((Word)look).lexeme);
            if(IdAddress == -1){
                IdAddress = count;
                st.insert(((Word)look).lexeme, count++);
            }
            match(Tag.ID);
            if(codop == 0){
                code.emit(OpCode.invokestatic ,0);
                code.emit(OpCode.istore, IdAddress);
            }
            if(codop == 2){
                code.emit(OpCode.istore, IdAddress);
            }
            idlistp(codop, num);
        }
        else{
            error("Error in idlist");
        }
    }
    
    /*GUIDE:
    Guida(idlistp -> ,ID<idlistp>) = {','}
    Guida(idlistp -> NULL) = {']',';',EOF,'}', end, option}
    */
    private void idlistp(int codop, int num){
        switch(look.tag){
            case ',':
                match(',');
                int IdAddress = st.lookupAddress(((Word)look).lexeme);
                if(IdAddress == -1){
                    IdAddress = count;
                    st.insert(((Word)look).lexeme, count++);
                }
                match(Tag.ID);
                if(codop == 0){
                    code.emit(OpCode.invokestatic ,0);
                    code.emit(OpCode.istore, IdAddress);
                }
                if(codop == 2){
                    code.emit(OpCode.ldc, num);
                    code.emit(OpCode.istore, IdAddress);
                }
                idlistp(codop, num);
            return;

            case ']':
            case ';':
            case Tag.EOF:
            case '}':
            case Tag.END:
            case Tag.OPTION:
            break;

            default:
                error("Error in idlistp");
            return;
        }

    }

    /*GUIDE:
    Guida(optlist -> <optitem><optlistp>) = {option}
    */
    private void optlist(int labelEndOpt){
        if(look.tag == Tag.OPTION){
            optitem(labelEndOpt);
            optlistp(labelEndOpt);
        }
        else{
            error("Error in optlist");
        }
        
    }

    /*GUIDE:
    Guida(optlistp -> <optitem><optlistp>) = {option}
    Guida(optlistp -> NULL) = {']'}
    */
    private void optlistp(int endLabel){
        switch(look.tag){
            case Tag.OPTION:
                optitem(endLabel);
                optlistp(endLabel);
            return;

            case']':
            break;

            default:
                error("Error in optlistp");
            return;
        }
    }

    /*GUIDE:
    Guida(optitem -> option(<bexpr>)do<stat>) = {option}
    */
    private void optitem(int endLabel){
        if(look.tag == Tag.OPTION){
            int LabelOptEnd = code.newLabel();
            match(Tag.OPTION);
            match('(');
            bexpr(3,LabelOptEnd);
            match(')');
            match(Tag.DO);
            stat();
            code.emit(OpCode.GOto, endLabel);
            code.emitLabel(LabelOptEnd);
        }
        else{
            error("Error in optitem");
        }
    }
    
    /*GUIDE:
    Guida(bexpr -> RELOP<expr><expr>) = {RELOP}
    */
    private void bexpr(int codop,int end_label){
        if(look.tag == Tag.RELOP){
            String s = "";
            s = ((Word)look).lexeme;
            
            match(Tag.RELOP);
            expr();
            expr();
            switch(codop){
                case 3:
                    switch(s){
                        case "<":
                            code.emit(OpCode.if_icmpge, end_label);
                        break;
                        case ">":
                            code.emit(OpCode.if_icmple, end_label);
                        break;
                        case "==":
                            code.emit(OpCode.if_icmpne, end_label);
                        break;
                        case "<=":
                            code.emit(OpCode.if_icmpgt, end_label);
                        break;
                        case ">=":
                            code.emit(OpCode.if_icmplt, end_label);
                        break;
                        case "<>":
                            code.emit(OpCode.if_icmpeq, end_label);
                        break;
                    }
                break;
            }
        }
        else{
            error("Error in bexpr");
        }
    }

    /*GUIDE:
    Guida(expr -> +(<exprlist>)) = {+}
    Guida(expr -> -<expr><expr>) = {-}
    Guida(expr -> *(<exprlist>)) = {*}
    Guida(expr -> /<expr><expr>) = {/}
    Guida(expr -> NUM) = {NUM}
    Guida(expr -> ID) = {ID}
    */
    private int expr(){
        switch(look.tag){
            case '+':
                match('+');
                match('(');
                exprlist(-1);
                code.emit(OpCode.iadd);
                match(')');
            break;

            case '-':
                match('-');
                expr();
                expr();
                code.emit(OpCode.isub);
            break;

            case '*':
                match('*');
                match('(');
                exprlist(-1);
                code.emit(OpCode.imul);
                match(')');
            break;

            case '/':
                match('/');
                expr();
                expr();
                code.emit(OpCode.idiv);
            break;

            case Tag.NUM:
                code.emit(OpCode.ldc, ((NumberTok)look).Number);
                int num = ((NumberTok)look).Number;
                match(Tag.NUM);
                
            return num;

            case Tag.ID:
                int idAddress = st.lookupAddress(((Word)look).lexeme);
                if(idAddress != -1){
                    code.emit(OpCode.iload, idAddress);
                }
                else{
                    error("Variabile non trovata");
                }
                match(Tag.ID);


                /*int label_while = code.newLabel();
                code.emit(OpCode.GOto, label_while);
                code.emitLabel(label_while);*/
            break;

            default:
                error("Error in expr");
            break;
        }

        return 0;
    }

    /*GUIDE:
    Guida(exprlist -> <expr><exprlistp>) = {+,-,*,/,NUM,ID}
    */
    private void exprlist(int codop){

        switch(look.tag){
            case '+':
            case '-':
            case '*':
            case '/':
            case Tag.NUM:
            case Tag.ID:
                expr();
                if(codop == 1){
                    code.emit(OpCode.invokestatic, 1);
                }
                exprlistp(codop);
            break;

            default:
                error("Error in exprlist");
            break;
        }

        
    }

    /*GUIDE:
    Guida(exprlistp -> ,<expr><exprlistp>) = {','}
    Guida(exprlistp -> NULL) = {')', ']'}
    */
    private void exprlistp(int codop){
        switch(look.tag){
            case ',':
                match(',');
                expr();
                if(codop == 1){
                    code.emit(OpCode.invokestatic, 1);
                }
                exprlistp(codop);
            return;
            
            case ')':
            case ']':
            break;

            default:
                error("Error in exprlistp");
            return;
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "input.lft"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Translator translator = new Translator(lex, br);
           
            translator.prog();
            System.out.println("input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }
}
