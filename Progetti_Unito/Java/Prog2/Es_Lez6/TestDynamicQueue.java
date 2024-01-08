public class TestDynamicQueue {
    public static void main(String[] args){
        PilaDinamica<Integer> PilaInt = new PilaDinamica<>();
        PilaInt.push(2);
        PilaInt.push(3);
        PilaInt.push(4);
        PilaInt.push(5);
        System.out.println("Pila.pop(): " + PilaInt.pop());
        System.out.println("4 uguale a 4?" + PilaInt.pop().equals(4));
        System.out.println("3 uguale a 4?" + PilaInt.pop().equals(4));

        PilaDinamica<String> PilaStr = new PilaDinamica<>();
        PilaStr.push("a");
        PilaStr.push("abc");
        PilaStr.push("abcd");
        PilaStr.push("abcde");
        System.out.println("Pila.pop(): " + PilaStr.pop());
        System.out.println("abcd uguale a abcd?" + PilaStr.pop().equals("abcd"));
        System.out.println("abc uguale a abcd?" + PilaStr.pop().equals("abcd"));

        PilaDinamica<Person> PilaPerson = new PilaDinamica<>();
        PilaPerson.push(new Person("Giorgio", "Chirio"));
        PilaPerson.push(new Person("Jeremy", "Celiberti"));
        PilaPerson.push(new Person("Tommaso", "Chirio"));
        PilaPerson.push(new Person("Riccardo", "Rossi"));
        System.out.println("Pila.pop(): " + PilaPerson.pop());
        System.out.println("Tommaso uguale a Tommaso?" + PilaPerson.pop().equals(new Person("Tommaso", "Chirio")));
        System.out.println("Jeremy uguale a Tommaso?" + PilaPerson.pop().equals(new Person("Tommaso", "Chirio")));


    }
}
