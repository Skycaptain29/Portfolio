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

    public void start() {
	if(look.tag == Tag.NUM || look.tag == '('){
        expr();
        match(Tag.EOF);
    }
	else{
        error("Start");
    }
    }

    private void expr() {
        term();
        exprp();
    }

    private void exprp() {
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp();
            return;
            case '-':
                match('-');
                term();
                exprp();
            return;
            default:
            return;
        }
    }

    private void term() {
        fact();
        termp();
    }

    private void termp() {
        switch (look.tag) {
            case '*':
                match('*');
                fact();
                termp();
            return;
            case '/':
                match('/');
                fact();
                termp();
            return;
            default:
            return;
        }
    }

    private void fact() {
        switch(look.tag){
            case Tag.NUM:
                match(Tag.NUM);
                return;
            case '(':
                match('(');
                expr();
                match(')');
                return;
            default:
                error("Fact Error" + (char)look.tag);
                return;
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