import java.awt.*;

public class Rettangolo_Colorato extends Rettangolo {
    private Color c;
    public Rettangolo_Colorato(int base, int altezza, Color c){
        super(base, altezza);
        this.c  = c;
    }

    public void draw(Graphics g){
        g.setColor(c);
        int b = base/2;
        int h = altezza/2;

        g.drawLine( b, h, -b, h); // disegno primo lato
        g.drawLine(-b, h, -b, -h); // disegno secondo lato
        g.drawLine(-b, -h, b, -h); // disegno terzo lato
        g.drawLine( b, -h, b, h); // disegno quarto lato
    }
}
