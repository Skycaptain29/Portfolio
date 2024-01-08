public class Test {
    public static void main(String[] args){
        Node<Integer> elem_2 = new Node<Integer>(10, null);
        Node<Integer> elem_1 = new Node<Integer>(11, elem_2);
        Node<Integer> top = new Node<Integer>(12, elem_1);

        Node<Integer> elem_1_2 = new Node<Integer>(13, null);
        Node<Integer> top_2 = new Node<Integer>(12, elem_1_2);

        System.out.print(controlla(top, top_2));
    }

    public static <T> boolean controlla(Node<T> p, Node<T> q) {
        if(q != null && p != null && p.elem.equals(q.elem)){
            return true;
        }
        else{
            if(q != null && p != null){
                return false || controlla(p.next, q.next);
            }
            else{
                return false;
            }
        }
    }
        
}
