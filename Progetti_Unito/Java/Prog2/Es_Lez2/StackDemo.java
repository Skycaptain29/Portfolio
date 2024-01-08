public class StackDemo
{
    public static void main(String[] args)
    {
        String[] a = {"a", "ba","vac","as","a"};
        Stack s = new Stack(3), t = new Stack(3);
        s.push(10); 
        s.push(20); 
        s.push(30);
        
        System.out.println("s = {10,20,30} e' pieno e diverso da t che e' vuoto");
        System.out.println(" s.full() = " + s.full());
        System.out.println(" s.empty() = " + s.empty());
        System.out.println(" s.equals(t) = " + s.equals(t));

        System.out.println("Eliminando uno dopo l'altro gli elementi in s otteniamo: ");
        System.out.println(" s.pop() = " + s.pop());
        System.out.println(" s.pop() = " + s.pop());
        System.out.println(" s.pop() = " + s.pop());

        System.out.println("Adesso s e' vuoto e uguale a t");
        System.out.println(" s.full() = " + s.full());
        System.out.println(" s.empty() = " + s.empty());
        System.out.println(" s.equals(t) = " + s.equals(t));

        System.out.println("Stringa più lunga array a");
        System.out.println(OpStringhe.longest(a));

        System.out.println("Concatenazione di stringhe in a");
        System.out.println(OpStringhe.concatAll(a));

        System.out.println("Trim di  ' A '");
        System.out.println("'"+ OpStringhe.Trim(" ads       ") +"'");

        Calcolatrice C = new Calcolatrice();

        System.out.println("Eseguo istruzioni 23+ (due piu' tre)");
        System.out.println(C.esegui( "23+" ) + "\n");
        
        System.out.println( "Eseguo istruzioni 23* (due per tre) ");
        System.out.println(C.esegui( "23*" ) + "\n");

        System.out.println("Eseguo istruzioni 23*9+ (due per tre piu' nove) ");
        System.out.println(C.esegui( "23*9+" ) + "\n");

        System.out.println("Eseguo istruzioni 99*9* (nove per nove per nove) ");
        System.out.println(C.esegui( "99*9*" ) + "\n");

        System.out.println("Eseguo istruzioni 99*9*1+ (nove per nove per nove piu' uno) ");
        System.out.println(C.esegui( "99*9*1+" ) + "\n");
    }
}