public class NodeUtil {
    public static <T> int size(Node<T> p){
        Node<T> tmpNode = p;
        int size = 0;
        while(tmpNode != null){
            size++;
            tmpNode = tmpNode.getNext();
        }
        return size;
    }
    public static <T> int occurrences(Node<T> p, T x){
        int occurrences = 0;
        Node<T> tmpNode = p;
        while(tmpNode != null){
            if(tmpNode.getElem().equals(x)){
                occurrences++;
            }
            tmpNode = tmpNode.getNext();
        }
        return occurrences;
    }

    public static <T> boolean included(Node<T> p, Node<T> q){
        boolean included = false;
        Node<T> tmpP = p;
        Node<T> tmpQ = q;
        while(tmpQ != null && tmpP != null){
            if(tmpP.getElem().equals(tmpQ.getElem())){
                tmpP = tmpP.getNext();
                included = true;
            }
            tmpQ = tmpQ.getNext();
        }
        if(tmpP != null){
            included = false;
        }
        return included;
    }
    /*ritorna true se tutti gli elementi nella lista p compaiono nello stesso ordine anche nella lista q,
    e false altrimenti. Per esempio, se p e q rappresentano rispettivamente le liste [1, 2, 3] e
    [0, 1, 2, 2, 0, 3, 4] il metodo deve ritornare true.*/

    public static <T> void printList(Node<T> p){
        Node<T> tempP = p;

        while(tempP != null){
            System.out.println(tempP.getElem());
            tempP = tempP.getNext();
        }
    }
    
    public static <T> void printWrapper(Node<T> p){
        Node<T> tempP = p;
        printListRic(tempP);
    }
    public static <T> void printListRic(Node<T> p){
        if(p != null){
        printListRic(p.getNext());
        System.out.println(p.getElem());
        }
           
    }
    //stampa la lista.

    public static <T> Node<T> reverse(Node<T> p){
        Node<T> temp = p;
        Node<T> newNode = null;
        int i = -1;
        while(temp != null){
            newNode = new Node<T>(temp.getElem(), newNode);
            temp = temp.getNext();
        }
        return newNode;
    }

    public static <T> Node<Integer> count(Node<Node<T>> p){
        Node<Node<T>> NodoTmp = p;
        Node<T> temp = null;
        Node<Integer> count = null;
        int i = 0;
        while(NodoTmp != null){
            temp = NodoTmp.getElem();
            while(temp != null){
                i++;
                temp = temp.getNext();
            }
            count = new Node<Integer>(i, count);
            i = 0;
            NodoTmp = NodoTmp.getNext();
        }
        return count;
    }
}
