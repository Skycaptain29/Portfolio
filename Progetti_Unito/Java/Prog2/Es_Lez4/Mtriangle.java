public class Mtriangle{
    private Mpoint a;
    private Mpoint b;
    private Mpoint c;

    Mtriangle(Mpoint a, Mpoint b, Mpoint c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Mpoint GetA(){return new Mpoint(a.X(), a.Y());}
    public Mpoint GetB(){return new Mpoint(a.X(), a.Y());}
    public Mpoint GetC(){return new Mpoint(a.X(), a.Y());}

    public void translate(double dx, double dy){
        a.Translate(dx,dy);
        b.Translate(dx,dy);
        c.Translate(dx,dy);
    }

    public void rotate(double angle){
        a.Rotate(angle);
        b.Rotate(angle);
        c.Rotate(angle);
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