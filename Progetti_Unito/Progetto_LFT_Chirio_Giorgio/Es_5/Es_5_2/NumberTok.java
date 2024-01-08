public class NumberTok extends Token {
    int Number = 0;
    public NumberTok(int tag, int n){
        super(tag);
        Number = n;
    }
    public String toString() { return "<" + tag + ", " + Number + ">"; }
}
    