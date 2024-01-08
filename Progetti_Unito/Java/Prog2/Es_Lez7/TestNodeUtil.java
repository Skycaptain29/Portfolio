public class TestNodeUtil {
    public static void main(String[] args){
        Node<Integer> Nodo1 = new Node<Integer>(3, null);
        Node<Integer> Nodo2 = new Node<Integer>(2, Nodo1);
        Node<Integer> Top = new Node<Integer>(1, Nodo2);

        Node<Integer> Node1 = new Node<Integer>(0, null);
        Node<Integer> Node2 = new Node<Integer>(1, Node1);
        Node<Integer> Node3 = new Node<Integer>(2, Node2);
        Node<Integer> Node4 = new Node<Integer>(2, Node3);
        Node<Integer> Node5 = new Node<Integer>(0, Node4);
        Node<Integer> Node6 = new Node<Integer>(3, Node5);
        Node<Integer> Top2 = new Node<Integer>(4, Node6);
        System.out.println(NodeUtil.size(Top));
        System.out.println(NodeUtil.occurrences(Top, 0));
        System.out.println(NodeUtil.included(Top, Top2));
        //NodeUtil.printListRic(Top);
        Node<Integer> reverse = NodeUtil.reverse(Top);
        NodeUtil.printListRic(reverse);

        Node<Integer> N1 = new Node<Integer>(3, null);
        Node<Integer> N2 = new Node<Integer>(2, N1);
        Node<Integer> N3 = new Node<Integer>(2, N2);
        Node<Integer> N4 = new Node<Integer>(2, N3);
        Node<Integer> Top3 = new Node<Integer>(1, N4);

        Node<Integer> No1 = new Node<Integer>(3, null);
        Node<Integer> Top4 = new Node<Integer>(1, No1);

        Node<Integer> Top5 = new Node<Integer>(1, null);

        Node<Node<Integer>> NodoNodo1 = new Node<Node<Integer>>(Top3, null);
        Node<Node<Integer>> NodoNodo2 = new Node<Node<Integer>>(Top4, NodoNodo1);
        Node<Node<Integer>> TopNodoNodo = new Node<Node<Integer>>(Top5, NodoNodo2);
        Node<Integer> count = NodeUtil.count(TopNodoNodo);
        Node<Integer> temp = count;
        while(temp != null){
            System.out.println(temp.getElem());
            temp = temp.getNext();
        }


        
    }
}
