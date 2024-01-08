import java.awt.*; //Abstract Window Toolkit
public class Triangolo extends Figura {
     // Un cerchio e' definito dal suo raggio r
     private int x;
     private int y;
     private int b;
     private int h;
     private Color c;
     // COSTRUTTORE di un quadrato
     public Triangolo(int x, int y, int b, int h, Color c){this.x = x; this.y = y; this.b = b; this.h = h; this.c = c;  }
     public void draw(Graphics g) {
        g.setColor(c);
        g.drawLine(x-(b/2), y, x+(b/2), y);
        g.drawLine(x-(b/2), y, x, y+h);
        g.drawLine(x+(b/2), y, x, y+h);
     }
}
