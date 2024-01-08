public class TestElicottero {
    public static void main(String[] args) {
    //creo un elicottero collocato all'origine del sistema di riferimento
    Elicottero a = new Elicottero(0, 0, 0);
    // visualizzo le coordinate dell'elicottero
    System.out.println( "Elicottero a di coordinate" );
    System.out.println("("+a.getX()+","+a.getY()+","+
     a.getAltitude()+")");
    // imposto la velocita' dell'elicottero a 500 km/h
    a.setSpeed(500);
    // imposto la velocita' verticale dell'elicottero a +10 km/h
    a.setVerticalSpeed(10);
    // imposto la direzione dell'elicottero a nord-nord-est (in radianti)
    a.setTrack((double) (3 * Math.PI / 8));
    System.out.println("velocita'="+a.getSpeed()+" velocita' verticale="
     + a.getVerticalSpeed()+" angolo="+a.getTrack());
    // faccio trascorrere mezz'ora
    a.elapse(0.5);
    // visualizzo le nuove coordinate: (95,230,5)
    System.out.println("Dopo mezz'ora di volo coordinate: ");
    System.out.println("("+a.getX()+","+a.getY()+","
     +a.getAltitude()+")");
    }
}

class Elicottero{
    int x;
    int y;
    double altitudine;
    int speed;
    double verticalSpeed;
    double track;

    public Elicottero(int x, int y, double altitudine){
        assert altitudine > 0: "altitudine < 0";
        this.x = x;
        this.y = y;
        this.altitudine = altitudine;
        speed = 0;
        verticalSpeed = 0.0;
        track = 0.0;
    }
}