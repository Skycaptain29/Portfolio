// Cerchio.java cerchio: un possibile oggetto "disegnabile"
// definiamo Cerchio come una sotto-classe di Figura

import java.awt.*; // Abstract Window Toolkit

public class Cerchio extends Figura {
    // un cerchio e' definito dal suo raggio r
    private int raggio;

    // COSTRUTTORE di un cerchio
    public Cerchio(int raggio) { this.raggio = raggio; }

    // OVERRIDE: ri-definiamo il metodo draw per disegnare una figura
    // in una finestra grafica g nel caso la figura sia un cerchio.

    // Disegniamo un cerchio centrato in (0, 0)

    public void draw(Graphics g) {
        g.drawOval(-raggio, -raggio, 2*raggio, 2*raggio);
    }
}
