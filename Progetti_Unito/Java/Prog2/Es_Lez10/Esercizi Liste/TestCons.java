public class TestCons {
    public static void main(String[] args){
        List lista = new Nil();
        lista.insert(0, 5);
        lista = lista.insert(1, 15);
        System.out.println(lista.toString());
    }
}
