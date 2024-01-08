// MyList.java
public class MyList {
    private Node first; // Riferimento al primo nodo della lista

    public MyList() {
        this.first = null;
    }

    public void insert(int elem) {
        first = new Node(elem, first);
    }

    public String toString() {
        String res = "";
        for (Node p = first; p != null; p = p.getNext()) {
            res += p.getElem();
            if (p.getNext() != null) res += ", ";
        }
        return res;
    }

    public void modifica(){
        int valprec = 0;
        Node tmpNode = first;
        while(tmpNode != null){
            valprec += tmpNode.getElem();
            tmpNode.setElem(valprec);
            tmpNode = tmpNode.getNext();
        }
    }

    public void pushSomma(){
        int sommaTot = 0;
        Node tmpNode = first;
        while(tmpNode != null){
            if(tmpNode.getElem() >= 0){
                sommaTot += tmpNode.getElem();
            }
            tmpNode = tmpNode.getNext();
        }
        insert(sommaTot);
    }
}
