public class RubricaDemo {
    public static void main(String[] args) {
        Rubrica R = new Rubrica(100);
        System.out.println("(1) Rubrica con contatti a,b,c: ");
        R.aggiungi("c","a@ditta");
        R.aggiungi("a","b@ditta");
        R.aggiungi("b","c@ditta");
        R.aggiungi("e","a@ditta");
        R.aggiungi("d","b@ditta");
        R.aggiungi("g","c@ditta");
        R.aggiungi("aw","a@ditta");
        R.aggiungi("aa","b@ditta");
        R.aggiungi("ab","c@ditta");
        R.scriviOutput();

        /*System.out.println("(2) Rimuovo a");
        R.rimuovi("a");
        R.scriviOutput();

        System.out.println("(3) Aggiungo b (ma c'e' gia'): successo = " + 
                           R.aggiungi("b","e"));
        R.scriviOutput();

        System.out.println("(4) Modifico b in b2: successo = " + 
                           R.cambiaNome("b","b2"));
        R.scriviOutput();

        System.out.println("(5) Modifico b@ditta in b2@ditta: successo = " + 
                           R.cambiaEmail("b2","b2@ditta"));
        R.scriviOutput();*/

        R.Sort();
        R.scriviOutput();
    }  
}