public class Nil extends List {
    public List insert(int n, int x) {
        return new Cons(x, this);
    }

    public String toString() {
        return "";
    }
}