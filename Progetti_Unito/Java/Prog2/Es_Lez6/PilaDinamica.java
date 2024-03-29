// Pila di dimensione variable di interi, 
// realizzata come lista concatenata di Nodi
public class PilaDinamica<T> {
    // Riferimento al nodo contenente l'elemento in cima alla pila
    // Invariante: top==null se la pila e' vuota, top!= null altrimenti.
    private Node<T> top;

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
    public void push(T x) {
        top = new Node<>(x, top);
    }

    // Elimina l'elemento in cima alla pila, e ritornane il valore.
    public T pop() {
        assert !vuota() : "non si puo' chiamare il metodo pop() se la pila e' vuota.";
        T tmp = top.getElem();
        top = top.getNext();
        return tmp;
    }
}