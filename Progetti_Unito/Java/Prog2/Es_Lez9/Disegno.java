// Disegno.java

import java.awt.*; // Abstract Window Toolkit (finestre grafiche)
import javax.swing.*; // estensione di AWT per interfacce grafiche

// Un "disegno" e' un JFrame con parte grafica = vettore di figure

public class Disegno extends JFrame {
    private static final long serialVersionUID = 1L;
    private Figura[] figure;
    
    // COSTRUTTORE basato sul costruttore della classe superiore JFrame
    public Disegno(Figura[] figure) {
        super(); // inizializziamo un JFrame
        this.figure = figure; // aggiungiamo un vettore di figure
    }

    // OVERRIDE: ridefiniamo il metodo "paint" di JFrame
    // chiedendo di inizializzare una finestra grafica,
    // poi di disegnare tutte le figure del vettore "figure" in g
    public void paint(Graphics g) {
        int w = getSize().width;    // base frame g
        int h = getSize().height;   // altezza frame g
        g.clearRect(0, 0, w, h);    // azzero contenuto del frame g
        g.translate(w/2, h/2);      // traslo il sistema di riferimento al centro del frame

        // DISEGNO tutte le figure del vettore "figure"
        for (int i=0; i < figure.length; i++)
            figure[i].draw(g);
            
        // BINDING per il metodo draw. Quale metodo draw viene scelto?
        // Dipende dal tipo esatto di figura[i]. Ad esempio, se figura[i]
        // ha tipo esatto Quadrato, allora viene scelto il metodo draw per i quadrati,
        // e non il metodo draw per le figure (che sarebbe un metodo vuoto)
    }

    public static void main(String[] args) {
        int m=70, n=90;
        Figura[] figure = new Figura[n];

        // Instanziamo le n figure: scegliamo m quadrati ed n-m cerchi
        for(int i = 0; i < m; i++)
            figure[i] = new PoligonoReg(i*7, 8);
            
        for(int i = m; i < n; i++)
            figure[i] = new Rettangolo_Colorato(i*4, i, Color.cyan);
            
        // definiamo un disegno con vettore di figure proprio "figure"
        Disegno frame = new Disegno(figure); // Jframe con vettore di figure

        // ESEMPI DI EREDITARIETA' (SENZA OVERRIDE)
        // Scegliamo di distruggere la figura quando ne chiudiamo la finestra
        // (il metodo setDefaultCloseOperation e' ereditato da JFrame)

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Impostiamo la dimensione della finestra grafica:
        // (il metodo setSize viene ereditato da JFrame)

        frame.setSize(600, 600);
        
        // Rendo il disegno visibile, con n figure disegnate da "paint":
        // (il metodo setVisible viene ereditato da JFrame)
        frame.setVisible(true);
    }
}



