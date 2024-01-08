import java.awt.*;

public class Quadrato_Colorato extends Quadrato{
    private Color c;
    public Quadrato_Colorato(int lato, Color c){
        super(lato);
        this.c = c;
    }

    public void draw(Graphics g) {
        g.setColor(c);
        int m = lato / 2;
        g.drawLine( m, m, -m, m); // disegno primo lato
        g.drawLine(-m, m, -m, -m); // disegno secondo lato
        g.drawLine(-m, -m, m, -m); // disegno terzo lato
        g.drawLine( m, -m, m, m); // disegno quarto lato
    }
}
