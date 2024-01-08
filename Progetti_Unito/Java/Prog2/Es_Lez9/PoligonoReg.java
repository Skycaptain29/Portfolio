import java.awt.*;

public class PoligonoReg extends Figura {

    private int r;
    private int n;

    public PoligonoReg(int r, int n){
        this.r = r;
        this.n = n;
    }

    public void draw(Graphics g){
        int x = 0, y = 0, x_next = 0, y_next = 0;
        for(int i = 0; i < n; i++){
            x = (int)Math.floor(r*Math.cos((2*Math.PI*i)/n));
            y = (int)(r*Math.sin((2*Math.PI*i)/n));
            x_next = (int)(r*Math.cos((2*Math.PI*(i+1))/n));
            y_next = (int)(r*Math.sin((2*Math.PI*(i+1))/n));
            g.drawLine(x, y, x_next, y_next);
        }
    }    
}
