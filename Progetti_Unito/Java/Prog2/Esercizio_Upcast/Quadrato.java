import java.awt.*; //Abstract Window Toolkit
    public class Quadrato extends Figura {
        //Un quadrato e' definito dal suo lato
        private int lato;
        private int x;
        private int y;
        private Color c;
        // COSTRUTTORE di un quadrato
        public Quadrato(int x, int y, int lato, Color c){ this.lato = lato; this.x = x; this.y = y; this.c = c; }
        // OVERRIDE: RI-definiamo il metodo draw (vuoto in Figura)
        // per disegnare una figura nel caso di un quadrato.
        // Scegliamo il quadrato centrato nell'origine e orizzontale.
        // Scegliamo il colore arancio per le prossime linee in g.
        public void draw(Graphics g){
            g.setColor(c);
            int m = lato/2;
            g.drawLine( x+m, y+m, x-m, y+m); //disegno primo lato in g
            g.drawLine( x-m, y+m, x-m, y-m); //disegno secondo lato in g
            g.drawLine( x-m, y-m, x+m, y-m); //disegno terzo lato in g
            g.drawLine( x+m, y-m, x+m, y+m); //disegno quarto lato in g
    }
}