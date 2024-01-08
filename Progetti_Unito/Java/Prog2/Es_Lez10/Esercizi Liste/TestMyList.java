public class TestMyList {
    public static void main(String[] args){
        MyList lista1 = new MyList();
        MyList lista2 = new MyList();
        lista1.insert(4);
        lista1.insert(0);
        lista1.insert(1);
        lista1.insert(-1);
        lista2.insert(-4);
        lista2.insert(-6);
        lista2.insert(7);
        lista2.insert(4);
        lista2.insert(-2);
        lista2.insert(2);

        lista1.modifica();
        System.out.println(lista1.toString());
        lista2.pushSomma();
        System.out.println(lista2.toString());
    }
}
