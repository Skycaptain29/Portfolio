import java.io.*; 

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '[':
                peek = ' ';
                return Token.lpq;
            case ']':
                peek = ' ';
                return Token.rpq;
            case '{':
                peek = ' ';
                return Token.lpg;
            case '}':
                peek = ' ';
                return Token.rpg;
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;
            case '/':
                peek = ' ';
                readch(br);
                if(peek == '/'){
                    while(peek != '\n'  && peek != '\r' && peek != (char)-1){
                        peek = ' ';
                        readch(br);
                    }
                    return lexical_scan(br);
                }
                else if(peek == '*'){
                    peek = ' ';
                    readch(br);
                    while(peek != (char)-1 ){
                        peek = ' ';
                        readch(br);
                        if(peek == '*'){
                            peek = ' ';
                            readch(br);
                            if(peek == '/'){
                                readch(br);
                                return lexical_scan(br);
                            }
                        }
                    }
                    System.err.println("Commento di tipo \"/* */\" non chiuso prima della fine del file");
                    return null;
                }
                else{
                    return Token.div;
                }
                
            case ';':
                peek = ' ';
                return Token.semicolon;
            case ',':
                peek = ' ';
                return Token.comma;
	
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : "  + peek );
                    return null;
                }
            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if(peek == '>') {
                    peek = ' ';
                    return Word.ne;
                }else{
                    peek = ' ';
                    return Word.lt;
                }
            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                }else{
                    peek = ' ';
                    return Word.gt;
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                }else{
                    System.err.println("Erroneous character"
                            + " after = : "  + peek );
                    return null;
                } 

	// ... gestire i casi di || < > <= >= == <> ... //
          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek) || peek == '_' || Character.isDigit(peek)){
                    int Stato = 0;
                    String str = "";
                    while(Character.isLetter(peek) || Character.isDigit(peek) || peek == '_'){
                        switch(Stato){
                            case 0:
                                if(Character.isLetter(peek)){
                                    Stato = 1;
                                    str += peek;
                                }
                                else if(peek == '_'){
                                    Stato = 2;
                                    str += peek;
                                }
                                else if(Character.isDigit(peek)){
                                    
                                    Stato = 3;
                                    str += peek;
                                }
                                else{
                                    Stato = -1;
                                }
                                
                            break;
                            case 1:
                                if(Character.isLetter(peek) || Character.isDigit(peek) || peek == '_'){
                                    Stato = 1;
                                    str += peek;
                                }
                                else{
                                    Stato = -1;
                                }
                            break;
                            case 2:
                                if(Character.isLetter(peek) || Character.isDigit(peek)){
                                    Stato = 1;
                                    str += peek;
                                }
                                else if(peek == '_'){
                                    Stato = 2;
                                    str += peek;
                                }
                                else{
                                    Stato = -1;
                                }
                            break;
                            case 3:
                                if(Character.isDigit(peek)){
                                    Stato = 3;
                                    str += peek;
                                }
                                else{
                                    Stato = -1;
                                }
                            break;
                        } 
                        peek = ' ';
                        readch(br);
                    }
                    if(Stato == 2){
                        System.err.println("Attenzione, gli identificatori non possono contenere solo il char '_'");
                        return null;
                    }
                    else if(Stato == 1){
                        switch (str){
                            case "assign":
                                return Word.assign;
                            case "to":
                                return Word.to;
                            case "conditional":
                                return Word.conditional;
                            case "option":
                                return Word.option;
                            case "do":
                                return Word.dotok;
                            case "else":
                                return Word.elsetok;
                            case "while":
                                return Word.whiletok;
                            case "begin":
                                return Word.begin;
                            case "end":
                                return Word.end;
                            case "print":
                                return Word.print; 
                            case "read":
                                return Word.read;
                            default:
                                return new Word(Tag.ID, str);
                        }
                    }
                    else if(Stato == 3){

                        return new NumberTok(Tag.NUM, Integer.parseInt(str));
                    }
                    else{
                        System.err.println("Attenzione, gli identificatori non possono iniziare con un numero");
                        return null;
                    }

                }else {
                    System.err.println("Erroneous character: " + peek );
                    return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}