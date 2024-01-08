abstract class A {
    public abstract void m1();
    
}
interface D{
    public void m4();
}
interface F{
    void m4();
}

class Class implements D,F{
    public void m4(){
        
    }
}

class ASD{
    public static void main(String[] Args){
        D obj = new Class(); obj.m4();
    }
}
abstract class B extends A {
    public void m1()
    { System.out.println("B.m1"); }
    public abstract void m2(A obj);
}
class C extends B {
    public void m1()
    {
        System.out.println("C.m1");
        super.m1();
    }
    public void m2(A obj)
    {
        System.out.println("B.m2");
        obj.m1();
    }
}

class abc{
    public static void main(String[] args){
        A obj = new C();
        obj.m1();

    }

    public static <T> boolean m4(T[][] a) {
        assert ok(a): "Matrice con righe vuote o Non quadrata";
        for (int i = 0; i < a.length; i++)
        for (int j = 0; j < a.length; j++)
        if (!a[i][j].equals(a[j][i])) return false;
        return true;
    }

    public static <T> boolean ok(T[][] a){
        for (int i = 0; i < a.length; i++)
            if(a[i] == null)
                return false;
        for (int i = 0; i < a.length; i++)
        for (int j = 0; j < a.length; j++)
            if(a.length != a[i].length)
                return false;
        return true;
    }
}

class G {
    public static void m1(boolean x)
    {
    if (x) throw new RuntimeException("m1");
    System.out.println("T");
    }

    public static boolean m2()
    {
    try {
        System.out.println("provo m1");
        m1(true);
        System.out.println("m1 OK");
    } catch (Exception e) {
        System.out.println("m1 EXC");
        return false;
    } finally {
        System.out.println("m2 FINITO");
        return true;
    }
    }

    public static void main(String[] args)
    {
    System.out.println(m2());
    }
}
    
