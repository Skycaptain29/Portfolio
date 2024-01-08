import java.io.*;

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
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
            break;

            case Tag.EOF:
            case '}':
            break;

            default:
                error("Error in statlistp");
            break;
        }
        

    }

    /*GUIDE:
    Guida(stat -> assign<expr>to<idlist>) = {assign}
    Guida(stat -> print[<exprlist>]) = {print}
    Guida(stat -> read[<idlist>]) = {read}
    Guida(stat -> while(<bexpr>)<stat>) = {while}
    Guida(stat -> conditional[<optlist>]<statp>) = {conditional}
    Guida(stat -> {<statlist>}) = {'{'}
    */
    private void stat(){
        switch(look.tag){
            case Tag.ASSIGN:
                match(Tag.ASSIGN);
                expr();
                match(Tag.TO);
                idlist();
            break;

            case Tag.PRINT:
                match(Tag.PRINT);
                match('[');
                exprlist();
                match(']');
            break;

            case Tag.READ:
                match(Tag.READ);
                match('[');
                idlist();
                match(']');
            break;

            case Tag.WHILE:
                match(Tag.WHILE);
                match('(');
                bexpr();
                match(')');
                stat();
            break;

            case Tag.COND:
                match(Tag.COND);
                match('[');
                optlist();
                match(']');
                statp();
            break;

            case '{':
                match('{');
                statlist();
                match('}');
            break;

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
    private void idlist(){
        if(look.tag == Tag.ID){
            match(Tag.ID);
            idlistp();
        }
        else{
            error("Error in idlist");
        }
    }

    /*GUIDE:
    Guida(idlistp -> ,ID<idlistp>) = {','}
    Guida(idlistp -> NULL) = {']',';',EOF,'}', end, option}
    */
    private void idlistp(){
        switch(look.tag){
            case ',':
                match(',');
                match(Tag.ID);
                idlistp();
            break;

            case ']':
            case ';':
            case Tag.EOF:
            case '}':
            case Tag.END:
            case Tag.OPTION:
            break;

            default:
                error("Error in idlistp");
            break;
        }
    }

    /*GUIDE:
    Guida(optlist -> <optitem><optlistp>) = {option}
    */
    private void optlist(){
        if(look.tag == Tag.OPTION){
            optitem();
            optlistp();
        }
        else{
            error("Error in optlist");
        }
        
    }

    /*GUIDE:
    Guida(optlistp -> <optitem><optlistp>) = {option}
    Guida(optlistp -> NULL) = {']'}
    */
    private void optlistp(){
        switch(look.tag){
            case Tag.OPTION:
                optitem();
                optlistp();
            break;

            case ']':
            break;

            default:
                error("Error in optlistp");
            break;
        }
    }

    /*GUIDE:
    Guida(optitem -> option(<bexpr>)do<stat>) = {option}
    */
    private void optitem(){
        if(look.tag == Tag.OPTION){
            match(Tag.OPTION);
            match('(');
            bexpr();
            match(')');
            match(Tag.DO);
            stat();
        }
        else{
            error("Error in optitem");
        }
        
    }

    /*GUIDE:
    Guida(bexpr -> RELOP<expr><expr>) = {RELOP}
    */
    private void bexpr(){
        if(look.tag == Tag.RELOP){
            match(Tag.RELOP);
            expr();
            expr();
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
    private void expr(){
        switch(look.tag){
            case '+':
                match('+');
                match('(');
                exprlist();
                match(')');
            break;

            case '-':
                match('-');
                expr();
                expr();
            break;

            case '*':
                match('*');
                match('(');
                exprlist();
                match(')');
            break;

            case '/':
                match('/');
                expr();
                expr();
            break;

            case Tag.NUM:
                match(Tag.NUM);
            break;

            case Tag.ID:
                match(Tag.ID);
            break;

            default:
                error("Error in expr");
            break;
        }
    }

    /*GUIDE:
    Guida(exprlist -> <expr><exprlistp>) = {+,-,*,/,NUM,ID}
    */
    private void exprlist(){
        switch(look.tag){
            case '+':
            case '-':
            case '*':
            case '/':
            case Tag.NUM:
            case Tag.ID:
                expr();
                exprlistp();
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
    private void exprlistp(){
        switch(look.tag){
            case ',':
                match(',');
                expr();
                exprlistp();
            break;

            case ')':
            case ']':
            break;

            default:
                error("Error in exprlistp");
            break;
        }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}