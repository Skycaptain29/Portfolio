public class Set<T> {
    private Node<T> first;
    private int size;

    public Set(){
        this.first = null;
    }

    //restituisce la size
    public int size(){
        return size;
    }

    //controlla se empty
    public boolean empty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //aggiunge elem di tipo T
    public void add(T elem){
        if(!contains(elem)){
            first = new Node<T>(elem, first);
            size += 1;
        }
        else{
            System.out.println("L'elemento è già nella lista");
        }
        
    }

    //rimuovi elem di tipo T
    public boolean remove(T elem){
        boolean remove = false;
        Node<T> tmpTop = first;
        Node<T> prev = null;
        Boolean flag = false;
        int i = -1;
        if(elem == first.getElem()){
            first = first.getNext();
        }
        else{
            while(tmpTop != null && !flag){
                i++;
                if(tmpTop.getElem() == elem){
                    flag = true;
                }       
                tmpTop = tmpTop.getNext();         
            }
            tmpTop = first;
            for(int j = 0; j < i;j++){
                if(j == i-1){
                    prev = tmpTop;
                }
                tmpTop = tmpTop.getNext();
            }
            Node<T> removeElem = prev.getNext();
            prev.setNext(removeElem.getNext());
            if(i != -1){
                remove = true;
            }
        }
        
        size -= 1;
        return remove;
    }

    //controlla se il set contiene l'elemento T
    public boolean contains(T elem){
        boolean contains = false;
        Node<T> tmpTop = first;
        while(tmpTop != null && !contains){
            if(tmpTop.getElem().equals(elem)){
                contains = true;
            }
            tmpTop = tmpTop.getNext();
        }
        return contains;
    }

    //controlla se il Set è il subset del Set2
    public boolean subsetOf(Set<T> Set){
        boolean subsetOf = true;
        Node<T> tmpTop = first;

        while(tmpTop != null && subsetOf){
            if(!Set.contains(tmpTop.getElem())){
                subsetOf = false;
            }
            tmpTop = tmpTop.getNext();
        }

        return subsetOf;
    }

    //controlla se i due set sono uguali
    public boolean equalsTo(Set<T> Set){
        boolean equalsTo = true;
        Node<T> tmpTop = first;

        if(size != Set.size()){
            equalsTo = false;
        }
        else{
            while(tmpTop != null && equalsTo){
                if(!Set.contains(tmpTop.getElem())){
                    equalsTo = false;
                }
                tmpTop = tmpTop.getNext();
            }
        }        
        

        return equalsTo;
    }

    public Set<T> union(Set<T> Set){
        Set<T> unionSet = new Set<T>();
        Node<T> tmpTop = first;
        Node<T> topS = Set.first;

        while(tmpTop != null){
            unionSet.add(tmpTop.getElem());
            tmpTop = tmpTop.getNext();
        }
        while(topS != null){
            unionSet.add(topS.getElem());
            topS = topS.getNext();
        }

        return unionSet;
    }

    public Set<T> intersection(Set<T> Set){
        Set<T> intersectionSet = new Set<T>();
        Node<T> tmpTop = first;

        while(tmpTop != null){
            if(Set.contains(tmpTop.getElem())){
                intersectionSet.add(tmpTop.getElem());
            }
            tmpTop = tmpTop.getNext();
        }

        return intersectionSet;
    }

    public void print(){
        Node<T> tmpTop = first;

        while(tmpTop != null){
            System.out.println(tmpTop.getElem());
            tmpTop = tmpTop.getNext();
        }
    }

}
