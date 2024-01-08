// Quadrato.java quadrato: un possibile oggetto "disegnabile"
// Definiamo Quadrato come una sotto-classe di Figura
 
import java.awt.*; // Abstract Window Toolkit

public class Quadrato extends Figura {
    // Un quadrato e' definito dal suo lato
    protected int lato;

    // COSTRUTTORE di un quadrato
    public Quadrato(int lato) { this.lato = lato; }

    // OVERRIDE: ri-definiamo il metodo draw (per ora vuoto)
    // per disegnare una figura nel caso di un quadrato.

    // Scegliamo che il quadrato sia centrato nell'origine
    // Scegliamo il colore arancio per le prossime linee in g

    public void draw(Graphics g) {
        int m = lato / 2;
        g.drawLine( m, m, -m, m); // disegno primo lato
        g.drawLine(-m, m, -m, -m); // disegno secondo lato
        g.drawLine(-m, -m, m, -m); // disegno terzo lato
        g.drawLine( m, -m, m, m); // disegno quarto lato
    }
}
