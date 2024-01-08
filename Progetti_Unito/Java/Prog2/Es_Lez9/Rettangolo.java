import java.awt.*;

public class Rettangolo extends Figura {
    protected int base;
    protected int altezza;
    public Rettangolo(int base, int altezza){
        this.base = base;
        this.altezza = altezza;
    }    

    public void draw(Graphics g){
        int b = base/2;
        int h = altezza/2;

        g.drawLine( b, h, -b, h); // disegno primo lato
        g.drawLine(-b, h, -b, -h); // disegno secondo lato
        g.drawLine(-b, -h, b, -h); // disegno terzo lato
        g.drawLine( b, -h, b, h); // disegno quarto lato
    }
}
