public class TestCast {
    public static void main(String[] args){
        // ESEMPIO. A seconda se la circostanza "oggi_piove" e' vera o
        // falsa, il tipo esatto di b e' la sottoclasse oppure la classe.
        boolean oggi_piove = true;
        //boolean oggi_piove = false;
        // Provate entrambe le possibilita’ qui sopra: una non compila
        // UPCAST: il passaggio a una classe superiore. E' sempre corretto.
        Bottiglia b;
    if (oggi_piove) b = new BottigliaConTappo(10);
        // upcast: b proviene da BottigliaConTappo ed e' spostato in
        // Bottiglia
    else b = new Bottiglia(10);
    // Se oggi_piove=true allora b si trova in BottigliaConTappo
    // Se oggi_piove=true allora b non si trova in BottigliaConTappo
    // DOWNCAST: di solito, passaggio a una classe inferiore.
    // Funziona SOLO nel caso in cui l'oggetto apparteneva GIA'
    // alla classe inferiore ed e' stato spostato nella superiore
    // da un upcast.
    // ESEMPIO. Il prossimo downcast appare corretto al compilatore
    // Java, il quale non ha modo di sapere se il tipo esatto di b e`
    // Bottiglia o BottigliaConTappo. A tempo di esecuzione viene
    // fatto un controllo sul tipo esatto di b e il downcast
    // fallisce (causando la terminazione anticipata del programma) se b
    // risulta avere tipo esatto Bottiglia.
    BottigliaConTappo t = (BottigliaConTappo) b;
    // SE b si trovava gia' in BottigliaConTappo ed e' stato spostato in
    // Bottiglia allora il downcast ha successo e scrivo:
    System.out.println( "Downcast avvenuto con successo");
    // ALTRIMENTI il programma termina con una ClassCastException
    // Dopo il downcast possiamo applicare a t un metodo aperta()
    // che esiste solo nella sottoclasse BottigliaConTappo
    System.out.println( "t.aperta() = " + t.aperta());
    // Non possiamo scrivere b.aperta(), anche se nell’esecuzione b=t:
    // System.out.println( "b.aperta() = " + b.aperta());
    // Il controllo di tipo del programma usa il tipo apparente
    // Bottiglia
    // di b, e nel tipo Bottiglia il metodo aperta non c'e'.
   }
   }

class Bottiglia{ //Nota: quantita' intere espresse in galloni
    private int capacita; // 0 <= capacita
    private int livello; // 0 <= livello <= capacita
    /* 0 <= capacita AND 0 <= livello <= capacita
    rappresenta un INVARIANTE di classe, ovvero una proprietà che definisce
    la buona formazione degli oggetti della classe Bottiglia: questa
    proprietà deve essere mantenuta vera dopo l’esecuzione del costruttore
    e dei metodi che manipolano gli oggetti di tipo Bottiglia. */

    public Bottiglia(int capacita){
        this.capacita = capacita;
        /** "this.capacita" e' un attributo di bottiglia mentre "capacita"
        e' l'unico argomento del costruttore Bottiglia */
        livello = 0;
        assert (0<=livello) && (livello <= capacita);
        // per assicurare che l'oggetto sia ben formato
    }
    /* Tutti i metodi che seguono sono dinamici (non-static), quindi hanno
    tutti un parametro 'this', oltre ai parametri espliciti elencati entro
    le loro parentesi (...). Il 'this' prende come valore l'indirizzo
    dell'oggetto su cui viene chiamato il metodo. Ricordiamo che la parolachiave this può essere lasciata implicita ovunque non si creino
    ambiguità.
    Un esercizio che si può fare è individuare in quali punti c'è il this
    implicito (e dove quindi si potrebbe rendere esplicito). */
    /** Aggiungiamo tutta la parte di una quantita' data che trova posto
    nella bottiglia (dunque il minimo tra la quantita' data e la capacita'
    residua). Restituiamo la quantita` che abbiamo aggiunto (che puo'
    essere meno della richiesta). */
    public int aggiungi(int quantita){
        assert quantita >= 0:
        "la quantita' doveva essere >=0 invece vale " + quantita;
        int aggiunta = Math.min(quantita, capacita-livello);
        livello = livello + aggiunta;
        assert (0<=livello) && (livello <= capacita);
        // dopo l'esecuzione del metodo l'oggetto deve essere ancora
        // ben formato <-- RISPETTIAMO L'INVARIANTE DI CLASSE STABILITO
        return aggiunta;
        /** min e' un metodo statico della classe Math, quindi fuori dalla
        classe Math lo indico con Math.min */
    }

    /** Rimuoviamo da una bottiglia una quantita' richiesta se c'e',
    altrimenti togliamo tutto (dunque il minimo tra la quantita'
    richiesta e il livello). Restituiamo la quantita' rimossa
    (che puo' essere meno della richiesta) */
    public int rimuovi(int quantita){
        assert quantita >= 0:
        "la quantita' doveva essere >=0 invece vale " + quantita;
        int rimossa = Math.min(quantita, livello);
        livello = livello - rimossa;
        assert (0<=livello) && (livello <= capacita);
        // dopo l'esecuzione del metodo l'oggetto deve essere ancora
        // ben formato <-- RISPETTIAMO L'INVARIANTE DI CLASSE STABILITO
        return rimossa;
    }
    public int getCapacita(){ return this.capacita; }
    public int getLivello() { return this.livello; }
    // Non consentiamo di cambiare la capacita`, quindi non c'è
    // un metodo setCapacita()
    public void setLivello(int livello){
        this.livello = livello;
        /** "this.livello" e' un attributo di bottiglia mentre "livello" e'
        l'unico argomento del metodo setLivello */
        assert (0<=livello) && (livello <= capacita);
    }
    public String toString() //conversione oggetto bottiglia --> stringa
    {return livello + "/" + capacita;}
}

class BottigliaConTappo extends Bottiglia {
    /* NUOVO attributo privato per memorizzare lo stato della bottiglia
    (true = bottiglia aperta, false = bottiglia chiusa) */
    private boolean aperta;
    /* NUOVO costruttore. Spesso dobbiamo definire un costruttore per le
    classi estese: raramente il costruttore di default fornito da Java per
    una estensione e' sensato */
    public BottigliaConTappo(int capacita){
    /* invochiamo il costruttore della classe superiore per fare le
    inizializzazioni della capacita' */
    super(capacita);
    // supponiamo che la bottiglia sia inizialmente chiusa
    aperta = false;
    }
    /* La chiamata al costruttore della classe superiore deve essere la
    prima istruzione del costruttore della sottoclasse. Il costruttore
    della classe superiore può essere richiamato solo dal costruttore della
    sottoclasse. Se il costruttore della sottoclasse non richiama
    esplicitamente un costruttore della classe superiore, per prima cosa
    viene chiamato automaticamente il costruttore predefinito della classe
    superiore, quello senza parametri (super()); se la classe superiore
    non ha un costruttore senza parametri il compilatore genera un errore.
    */
    // NUOVO metodo get per sapere se la bottiglia e` aperta o chiusa
    public boolean aperta(){ return aperta; }
    // NUOVO metodo per aprire la bottiglia
    public void apri() { aperta = true; }
    // NUOVO metodo per chiudere la bottiglia
    public void chiudi() { aperta = false; }
    // Ereditiamo i metodi get, set e toString() da Bottiglia
    /* OVERRIDE del metodo "aggiungi" per versare liquido nella bottiglia:
    richiediamo che la bottiglia sia aperta. Dal momento che "aggiungi"
    deve restituire la quantita` di liquido aggiunto anche nel caso in cui
    la bottiglia sia chiusa, dobbiamo restituire un valore sensato (0 in
    questo caso) */
    public int aggiungi(int quantita){
    if (aperta)
     return super.aggiungi(quantita); /*super.aggiungi() indica il metodo
    "aggiungi" nella classe Bottiglia che stiamo estendendo */
    else return 0;
    }
    /* OVERRIDE del metodo "rimuovi" per versare liquido dalla bottiglia:
    stesse osservazioni */
    public int rimuovi(int quantita){
     if (aperta)
     return super.rimuovi(quantita); /*super.rimuovi() indica il metodo
    "rimuovi" nella classe Bottiglia che stiamo estendendo */
     else return 0;
    }
    /* OVERRIDE del metodo "toString()". Alla stringa che descrive una
    bottiglia aggiungiamo l’informazione aperta/chiusa */
    public String toString(){
     return super.toString() + " (aperta = " + aperta + ")";
    }
    }