import java.io.*;
public class Valutatore {
private Lexer lex;
private BufferedReader pbr;
private Token look;
public Valutatore(Lexer l, BufferedReader br) {
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
    int expr_val;
    if(look.tag == '(' || look.tag == Tag.NUM){
        expr_val = expr();
        match(Tag.EOF);
        System.out.println("Result: " + expr_val);
    }
    else{
        error("Error in start");
    }
    
}

/*GUIDE:
    Guida(expr -> <term><exprp>) = {(, NUM}
*/
private int expr() {
    int term_val, exprp_val;
    if(look.tag == '(' || look.tag == Tag.NUM){
        term_val = term();
        exprp_val = exprp(term_val);
        return exprp_val;
    }
    else{
        error("Error in expr");
        return 0;
    }
}

/*GUIDE:
    Guida(exprp -> +<term><exprp>) = {+}
    Guida(exprp -> -<term><exprp>) = {-}
    Guida(exprp -> null>) = { EOF, )}
*/
private int exprp(int exprp_i) {
int term_val, exprp_val;
    
    switch (look.tag) {
        case '+':
            match('+');
            term_val = term();
            exprp_val = exprp(exprp_i + term_val);
        break;

        case '-':
            match('-');
            term_val = term();
            exprp_val = exprp(exprp_i - term_val);
        break;

        case Tag.EOF:
        case ')':
            exprp_val = exprp_i;
        break;

        default:
            exprp_val = exprp_i;
            error("Error in exprp");
        break;
    }
    return exprp_val;
}

/*GUIDE:
    Guida(term -> <fact><termp>) = {(, NUM}
*/
private int term() {
    if(look.tag == '(' || look.tag == Tag.NUM){
        int fact_val, term_val;
        fact_val = fact();
        term_val = termp(fact_val);
        return term_val;
    }
    else{
        error("Error in term");
        return 0;
    }
    
}

/*GUIDE:
    Guida(termp -> *<fact><termp>) = {*}
    Guida(termp -> /<fact><termp>) = {/}
    Guida(termp -> null) = {+,-,EOF,)}
*/
private int termp(int termp_i) {
    int fact_val, termp_val;
    
    switch (look.tag) {
        case '*':
            match('*');
            fact_val = fact();
            termp_val = termp(termp_i * fact_val);
        break;

        case '/':
            match('/');
            fact_val = fact();
            termp_val = termp(termp_i / fact_val);
        break;

        case '+':
        case '-':
        case Tag.EOF:
        case ')':
            termp_val = termp_i;
        break;

        default:
            termp_val = termp_i;
            error("Error in termp");
        break;
    }
    return termp_val;
}

/*GUIDE:
    Guida(fact -> (<expr>)) = {(}
    Guida(fact -> NUM)) = {NUM}
*/
private int fact() {
    int fact_val = 0;
    switch(look.tag){
        case '(':
            match('(');
            fact_val = expr();
            match(')');
            break;
        case Tag.NUM:
            fact_val = ((NumberTok)look).Number;
            match(Tag.NUM);
            break;
        default:
            error("Error in fact");
    }
    return fact_val;

}
public static void main(String[] args) {
    Lexer lex = new Lexer();
    String path = "test.txt"; // il percorso del file da leggere
    try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        Valutatore valutatore = new Valutatore(lex, br);
        valutatore.start();
        br.close();
    } catch (IOException e) 
    {e.printStackTrace();}
    }
}
