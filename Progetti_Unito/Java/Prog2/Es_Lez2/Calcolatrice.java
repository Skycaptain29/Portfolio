public class Calcolatrice {
    Stack stack = new Stack(100);

    public int esegui(String istruzioni) {
        int numeroIstruzioni = istruzioni.length(); //lunghezza
        int pc = 0; // inizio leggendo la prima istruzione, in posizione 0

        while (pc < numeroIstruzioni) { //eseguo le istruzioni in ordine
            char c = istruzioni.charAt(pc); //c = carattere di posto pc

            if (c >= '0' && c <= '9') { //vero se c e' una cifra
                stack.push(c - '0');  //questa formula mi da' il valore della cifra c
            } 
            else if (c == '+') {
                int ultimo = stack.pop(); //risultato ultimo calcolo
                int penultimo = stack.pop(); //risultato penultimo calcolo
                stack.push(penultimo + ultimo);
            } 
            else if (c == '*') {
                int ultimo = stack.pop(); //risultato ultimo calcolo
                int penultimo = stack.pop(); //risultato penultimo calcolo
                stack.push(penultimo * ultimo);
            }

            pc++; // passiamo alla prossima istruzione
        }

        return stack.pop(); //alla fine restituisco l'ultimo risultato ottenuto
    }

}
