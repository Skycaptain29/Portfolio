import org.jcp.xml.dsig.internal.dom.ApacheNodeSetData;

abstract class Tree<T> {
    public abstract Tree<T> detach(T x);
    public abstract void Stampa();
    public void a2(){
        
    };
}
class Leaf<T> extends Tree<T> {
    public Tree<T> detach(T x) {
        return this;
    }
    public void Stampa(){
        System.out.println();
    }
}
 class Branch<T> extends Tree<T> {
    private T elem;
    private Tree<T> left;
    private Tree<T> right;
    public Branch(T elem, Tree<T> left, Tree<T> right) {
        this.elem = elem;
        this.left = left;
        this.right = right;
    }
    public Tree<T> detach(T x) {
        if(elem == x){
            return new Leaf<T>();
        }
        else{
            left = left.detach(x);
            right = right.detach(x);
            return this;
        }
    }
    public void Stampa(){
        System.out.println(elem);
        left.Stampa();
        right.Stampa();
    }
    
}
class ABC{
    public static void main(String[] args){
        Tree<Integer> t = new Branch<Integer>(2, new Branch<Integer>(-3, new Leaf<Integer>(), new Branch<Integer>(1, new Leaf<Integer>(), new Leaf<Integer>())),new Leaf<Integer>());
        t.detach(1);
        t.Stampa();
    }
}

interface I{
    public void m1(J obj);
}
interface J{
    public void m2();
}
abstract class C implements I{
    public abstract void m1(J obj);
}
class D extends C implements J{
    public void m1(J obj) {
        if (this != obj) obj.m2();
        System.out.println("D.m1");
        }
        public void m2() {
            System.out.println("D.m2");
            m1(this);
        }
}
class es34{
    public static void main(String[] args){
        I obj = new D();
        ((D) obj).m2(); //D.m2  D.m1

        J obj2 = new D();
        C x = (C)obj2;
        x.m1(new D()); //D.m2  D.m1 D.m1
        //assert OK(Node<T> p, T x): "ERRORE";
    }

    /*public static <T> boolean OK(Node<T> p, T x){
        boolean flag = false;
        if(p == null || x == null){
            flag = true;
        }
        else{
            Node<T> tmp = p;
            while(tmp != null && !flag){
                if(tmp.getElem() == null){
                    flag = true;
                }
                tmp = tmp.getNext();
            }
        }
        return flag;
    }*/
}

class Node<T> {
    public T elem;
    public Node<T> next;
    public Node(T elem, Node<T> next) {
    this.elem = elem;
    this.next = next;
    }
}
public class Ex4{
    public static <T> boolean controlla(Node<T> p, Node<T> q) {
        if(p == null || q == null){
            return false;
        }
        else{
            if(p.elem == q.elem){
                return true;
            }
            else{
                return controlla(p.next, q.next);
            }
        }
    }
}