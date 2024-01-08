public class Ipoint {
    private double X;
    private double Y;

    public Ipoint(double X, double Y){
        this.X = X;
        this.Y = Y;
    }

    public double X(){
        return X;
    }
    public double Y(){
        return Y;
    }

    public Ipoint TranslateImmobile(double X, double Y){

        return new Ipoint(this.X + X, this.Y+Y);
    }

    public Ipoint RotateImmobile(double alpha){
        alpha = Math.toRadians(alpha);
        double old_x = this.X;
        double old_y = this.Y;
        double new_X = Math.round((old_x*Math.cos(alpha)) - (old_y*Math.sin(alpha)));
        double new_Y = Math.round((old_x*Math.sin(alpha)) + (old_y*Math.cos(alpha)));
        return new Ipoint(new_X,new_Y);
    }

    public double Distance(Mpoint p_2){
        //Un metodo per calcolare la distanza del punto da un altro punto dato come parametro (come
        //verificare che il secondo punto, passato come argomento, sia valido?).
        
        double distance = Math.sqrt((Math.pow((X - p_2.X()), 2))+(Math.pow((Y - p_2.Y()), 2)));
        return distance;
    }

    public void Print(){
        System.out.println("X: " + X+ " Y:" + Y);
    }
}
