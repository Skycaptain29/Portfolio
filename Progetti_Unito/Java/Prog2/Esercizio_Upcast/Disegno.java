import java.awt.*; //Abstract Window Toolkit (finestre grafiche)
import javax.swing.*; //estensione di awt per interfacce grafiche
public class Disegno extends JFrame {
    /* Un "disegno" e' un JFrame con parte grafica = tutte le figure di
    un array di figure */
    private Figura[] figure;
    //COSTRUTTORE basato sul costruttore della classe superiore JFrame
    public Disegno(Figura[] figure){
        super(); // Assegnamo tutti i parametri di un JFrame
        this.figure = figure; // Aggiungiamo un array di figure
        }

        // OVERRIDE: ridefiniamo il metodo paint di JFrame
        // chiedendo di inizializzare una finestra grafica, poi
        // di disegnare tutte le figure dell’array figure di g
        public void paint(Graphics g) { //INIZIALIZZO g
            int w = this.getSize().width; // base di g
            int h = this.getSize().height; // altezza di g
            g.clearRect(0, 0, w, h); // azzero il contenuto di g
            g.translate(w/2,h/2); //traslo sistema di riferimento al centro di g
            //DISEGNO tutte le figure dell’array figure
            for(int i=0;i<figure.length;++i) figure[i].draw(g);
        }
    //BINDING per il metodo draw. Quale metodo draw viene scelto?
    //Dipende dal tipo esatto di figura[i]. Se figura[i] ha tipo esatto
    //Quadrato, allora viene scelto il metodo draw per i quadrati e non
    //il metodo draw per le figure (che sarebbe un metodo vuoto)
    public static void main(String[] args){
        int m=70,n=90, z = 100; Figura[] figure = new Figura[z];
        //Array di n figure: all’inizio ogni figura vale null
        //Assegnamo le n figure: scegliamo m quadrati e (n-m) cerchi
        //Possiamo farlo perche' quadrati e cerchi sono particolari figure
        for(int i = 0; i<m; i++) figure[i] = new Quadrato(i, i-10, i*7, Color.BLUE);
        for(int i = m; i<n; i++) figure[i] = new Cerchio(i/2, i, i*3, Color.orange);
        for(int i = n; i<z; i++) figure[i] = new Triangolo(i/4, i+20, i*4, i*2, Color.black);
        //Definiamo un disegno con array di figure proprio “figure"
        Disegno frame = new Disegno(figure);//Jframe con array di figure
        //ESEMPI DI EREDITARIETA' (SENZA OVERRIDE) DALLA CLASSE JFRAME
        //Scegliamo di terminare la figura quando ne chiudiamo la finestra
        //(il metodo setDefaultCloseOperation viene ereditato da JFrame)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Scegliamo la dimensione della finestra grafica:
        //(il metodo setSize viene ereditato da JFrame)
        frame.setSize(600,600);
        // setVisible(true) rende il disegno visibile, inviando il metodo
        // paint all’oggetto frame insieme con un oggetto grafico g:
        //(il metodo setVisible viene ereditato da JFrame)
        frame.setVisible(true);
    }
}
