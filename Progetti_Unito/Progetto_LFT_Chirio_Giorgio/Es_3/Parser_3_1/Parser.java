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
        } else error("syntax error");
    }

    /*GUIDE:
    Guida(start -> <expr>EOF) = {(, NUM}
    */
    public void start() {
        if(look.tag == Tag.NUM || look.tag == '('){
            expr();
            match(Tag.EOF);
        }
        else{
            error("Error in start");
        }
    }

    /*GUIDE:
    Guida(expr -> <term><exprp>) = {(, NUM}
    */
    private void expr() {
        if(look.tag == Tag.NUM || look.tag == '('){
            term();
            exprp();
        }
        else{
            error("Error in expr");
        }
    }


    /*GUIDE:
    Guida(exprp -> +<term><exprp>) = {+}
    Guida(exprp -> -<term><exprp>) = {-}
    Guida(exprp -> null>) = { EOF, )}
    */
    private void exprp() {
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp();
            break;

            case '-':
                match('-');
                term();
                exprp();
            break;

            case Tag.EOF:
            case ')':
            break;

            default:
                error("Error in exprp");
            break;
        }
    }

    /*GUIDE:
    Guida(term -> <fact><termp>) = {(, NUM}
    */
    private void term() {
        if(look.tag == Tag.NUM || look.tag == '('){
            fact();
            termp();
        }
        else{
            error("Error in term");
        }
    }

    /*GUIDE:
    Guida(termp -> *<fact><termp>) = {*}
    Guida(termp -> /<fact><termp>) = {/}
    Guida(termp -> null) = {+,-,EOF,)}
    */
    private void termp() {
        switch (look.tag) {
            case '*':
                match('*');
                fact();
                termp();
            break;

            case '/':
                match('/');
                fact();
                termp();
            break;

            case '+':
            case '-':
            case Tag.EOF:
            case ')':
            break;

            default:
                error("Error in termp");
            break;
        }
    }
    /*GUIDE:
    Guida(fact -> (<expr>)) = {(}
    Guida(fact -> NUM)) = {NUM}
    */
    private void fact() {
        switch(look.tag){
            case Tag.NUM:
                match(Tag.NUM);
            break;

            case '(':
                match('(');
                expr();
                match(')');
            break;

            default:
                error("Error in fact");
            break;
        }
        
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "test.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}