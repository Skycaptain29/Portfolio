public class Itriangle {
    private Ipoint a;
    private Ipoint b;
    private Ipoint c;

    Itriangle(Ipoint a, Ipoint b, Ipoint c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Ipoint GetA(){return new Ipoint(a.X(), a.Y());}
    public Ipoint GetB(){return new Ipoint(a.X(), a.Y());}
    public Ipoint GetC(){return new Ipoint(a.X(), a.Y());}

    public Itriangle translate(double dx, double dy){
        return new Itriangle(a.TranslateImmobile(dx,dy), b.TranslateImmobile(dx,dy), c.TranslateImmobile(dx,dy));
    }

    public Itriangle rotate(double angle){
        return new Itriangle(a.RotateImmobile(angle), b.RotateImmobile(angle), c.RotateImmobile(angle));
    }

    public double perimeter(){
        return Math.abs(a.Distance(b)) + Math.abs(a.Distance(b)) + Math.abs(b.Distance(c));
    }

    public double area(){
        return Math.abs(((a.X()*(b.Y()-c.Y())) + (b.X()*(c.Y()-a.Y())) + (c.X()*(a.Y()-b.Y())))/2);
    }

    public String toString(){
        return "a:" + a.toString() + " b:" + b.toString() + " c:" + c.toString();
    }
}
