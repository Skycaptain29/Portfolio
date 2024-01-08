public class TriangleTest {
    public static void main(String args[]){
        Mpoint a = new Mpoint(5,5);
        Mpoint b = new Mpoint(10,15);
        Mpoint c = new Mpoint(15,5);
        Mtriangle triangolo = new Mtriangle(a,b,c);
        System.out.println(triangolo.toString());
        System.out.println(triangolo.area());
        System.out.println(triangolo.perimeter());
        triangolo.translate(5,0);
        System.out.println(triangolo.toString());
        Mpoint a2 = triangolo.GetA();
        a2.Translate(10,10);
        System.out.println(triangolo.toString());
        Ipoint d = new Ipoint(5,5);
        Ipoint e = new Ipoint(10,15);
        Ipoint f = new Ipoint(15,5);
        Itriangle Itriangolo = new Itriangle(d,e,f);
        Itriangle ItriangoloTranslate = Itriangolo.translate(5,0);
        System.out.println(Itriangolo.toString());
        System.out.println(ItriangoloTranslate.toString());
    }
}
