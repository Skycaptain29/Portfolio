public class TestMatita {
    public static void main(String[] args){
    Matita m = new Matita(Matita.maxStelo);
     int s = m.getStelo(), p = m.getPunta();
    System.out.println("Matita di stelo " + s + " e punta " + p);
     System.out.println("Disegno per " + 2*p + " volte:");
    System.out.println("dopo " + p + " volte il disegno fallisce");
    for (int i = 0; i < 2*p; i++)
     System.out.println(" Successo disegno n."+i+" = "+m.disegna());
    System.out.println("Tempero di 1mm la matita"); m.tempera();
    System.out.println(" nuova lunghezza punta = " + m.getPunta());
    System.out.println(" nuova lunghezza stelo = " + m.getStelo());
    System.out.println("Stampo la matita m. Ottengo \"Matita@\" seguito dall'indirizzo dell'oggetto (in esadecimale): " + m);
     }
}


class Matita{
    public static final int minStelo = 10;
    public static final int maxStelo = 200;
    public static final int maxPunta = 5;
    private int stelo = 0;
    private int punta = 0;

    public Matita(int stelo){
        assert stelo > minStelo && stelo < maxStelo : "errore di assegnazione lunghezza stelo";
        this.stelo = stelo;
        this.punta = maxPunta;
    }

    public int getStelo(){return this.stelo;}
    public int getPunta(){return this.punta;}

    public boolean disegna(){
        boolean disegno = false;
        if(punta > 0){
            this.punta -= 1;
            disegno = true;
        }
        return disegno;
    }
    public boolean tempera(){
        boolean temperata = false;
        if(this.stelo > 1){
            temperata = true;
            this.stelo -= 1;
            if(this.punta < maxPunta){
                this.punta += 1;
            }
        }
        return temperata;
    }
    public static int maxStelo(){
        return maxStelo;
    }

}
    
