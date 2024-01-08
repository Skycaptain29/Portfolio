public class pointDemo {
    public static void main(String[] args)
    {
        Ipoint punto = new Ipoint(1, 1);
        Ipoint punto_2;

        System.out.println("X: "+punto.X() + " Y:" +punto.Y());
        punto_2 = punto.TranslateImmobile(1,1);
        punto_2.Print();
    }
}
