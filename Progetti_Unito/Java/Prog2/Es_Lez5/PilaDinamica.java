// Pila di dimensione variable di interi, 
// realizzata come lista concatenata di Nodi
public class PilaDinamica {
    // Riferimento al nodo contenente l'elemento in cima alla pila
    // Invariante: top==null se la pila e' vuota, top!= null altrimenti.
    private Node top;

    // Costruttore: crea una pila vuota
    public PilaDinamica() {
        this.top = null;
    }

    // Ritorna true se la pila e' vuota
    public boolean vuota() {
        return this.top == null;
    }

    // Ritorna true se la pila e' piena. 
    // Poiche' la pila ha dimensione dinamica, non puo' mai 
    // essere piena, a meno di finire la memoria
    public boolean piena() {
        return false;
    }

    // Aggiungi l'intero @x in cima alla pila
    public void push(int x) {
        top = new Node(x, top);
    }

    // Elimina l'elemento in cima alla pila, e ritornane il valore.
    public int pop() {
        assert !vuota() : "non si puo' chiamare il metodo pop() se la pila e' vuota.";
        int tmp = top.getElem();
        top = top.getNext();
        return tmp;
    }
    public int[] toArray(){
        assert !vuota(): "Pila vuota";
        int[] Returnarray = new int[1];
        while(!vuota()){
            Returnarray[0] = pop();
            if(!vuota()){
                Returnarray = aggiungiSpazioInizio(Returnarray);
            }
        }

        return Returnarray;
    }
    public int[] aggiungiSpazioInizio(int[] old){
        int[] nuovo = new int[old.length+1];
        int j = nuovo.length-1;
        for(int i = old.length-1; i >= 0; i--){
            nuovo[j] = old[i];
            j -= 1;
        }
        return nuovo;
    }
}