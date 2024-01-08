public class TestSet {
    public static void main(String[] args){
        Set<Integer> Set1 = new Set<Integer>();
        Set<Integer> Set2 = new Set<Integer>();
        System.out.println(Set1.empty());
        Set1.add(1);
        Set1.add(2);
        Set1.add(3);
        Set1.add(4);
        Set1.add(5);
        System.out.println(Set1.size());
        System.out.println("Rimozione 2: " + Set1.remove(2));
        System.out.println("Set1 ");Set1.print();

        System.out.println(Set1.size());
        Set1.add(2);
        
        System.out.println(Set1.contains(1));
        
        Set2.add(3);
        Set2.add(4);
        
        
        System.out.println(Set1.subsetOf(Set2));
        
        System.out.println(Set1.equalsTo(Set2));
        Set<Integer> SetUnion = Set1.union(Set2);
        Set<Integer> SetIntersection = Set1.intersection(Set2);

        System.out.println("Set1 ");Set1.print();
        System.out.println("Set2 ");Set2.print();
        System.out.println("Unione ");SetUnion.print();
        System.out.println("Intersezione ");SetIntersection.print();

    }
}
