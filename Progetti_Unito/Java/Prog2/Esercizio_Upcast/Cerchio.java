import java.awt.*; //Abstract Window Toolkit
public class Cerchio extends Figura {
    // Un cerchio e' definito dal suo raggio r
    private int raggio;
    private int x;
    private int y;
    private Color c;

    // COSTRUTTORE di un quadrato
    public Cerchio(int x, int y, int raggio, Color c){ this.raggio = raggio; this.x = x; this.y = y; this.c = c; }
    public void draw(Graphics g) {
        g.setColor(c);
        g.drawOval(x-raggio,y-raggio, 2*raggio,2*raggio);
    }
}
