public class PilaTest {

    public static void main(String[] args){
        PilaDinamica pila = new PilaDinamica();
        pila.push(1);
        pila.push(2);
        int[] a = pila.toArray();
        for(int i = 0; i < a.length; i ++){
            System.out.println(a[i]);
        }
        Node top = fromTo(-1,2);
        Node topStampa = top;
        while(topStampa != null){
            System.out.println(topStampa.getElem());
            topStampa = topStampa.getNext();
        }
        Node top2 = fromTo(-1,-2);
        if(top2 == null){
        System.out.println("vuoto");
        }

        Node n1 = new Node(1, null);
        Node n2 = new Node(1, n1);
        Node n3 = new Node(2, n2);
        Node n4 = new Node(1, n3);
        Node n5 = new Node(0, n4);

        Node q1 = new Node(0, null);
        Node q2 = new Node(1, q1);
        Node q3 = new Node(2, q2);
        Node q4 = new Node(2, q3);
        Node q5 = new Node(0, q4);
        Node q6 = new Node(3, q5);
        Node q7 = new Node(4, q6);

        Node p1 = new Node(1, null);
        Node p2 = new Node(2, p1);
        Node p3 = new Node(3, p2);

        Node z1 = new Node(4, null);
        Node z2 = new Node(5, z1);
        Node z3 = new Node(6, z2);

        System.out.println(occurrences(n5,1));
        System.out.println(included(p3,q7));

        Node TestReverse = reverse(p3);
        Node TestReverseRic = ReverseRic(p3);
        while(TestReverse != null){
            System.out.println(TestReverse.getElem());
            TestReverse = TestReverse.getNext();
        }
        while(TestReverseRic != null){
            System.out.println(TestReverseRic.getElem());
            TestReverseRic = TestReverseRic.getNext();
        }
        System.out.println("---------------------");
        Node TestZip = zipSum(p3, z3);
        Node TestZipRic = zipSumRic(p3, z3);
        while(TestZip != null){
            System.out.println(TestZip.getElem());
            TestZip = TestZip.getNext();
        }
        while(TestZipRic != null){
            System.out.println(TestZipRic.getElem());
            TestZipRic = TestZipRic.getNext();
        }

        
    }

    public static Node fromTo(int m, int n){
        Node top = null;
        if(m < n){
            for(int i = n; i >= m; i--){
                top = new Node(i, top);
            }
        }
        return top;
        
    }

    public static int occurrences(Node p, int x){
        int occurrences = 0;
        while(p != null){
            if(p.getElem() == x){
                occurrences += 1;
            }
            p = p.getNext();
        }
        return occurrences;
    }

    public static boolean included(Node p, Node q){
        Node pTemp = p;
        Node qTemp = q;
        boolean flagGlobal = true;
        boolean flag = false;
        while(pTemp != null){
            int numP = pTemp.getElem();
            while(qTemp != null){
                if(numP == qTemp.getElem()){
                    flag = true;
                }
                qTemp = qTemp.getNext();
            }
            flagGlobal = flagGlobal && flag;
            flag = false;
            pTemp = pTemp.getNext();
            qTemp = q;
        }
        return flagGlobal;
    }
    public static Node reverse(Node p){
        int[] Numarray = new int[1];
        Node pAus = p;
        Node top = null;
        while(pAus != null){
            Numarray[0] = pAus.getElem();
            pAus = pAus.getNext();
            if(pAus != null){
                Numarray = aggiungiSpazioInizio(Numarray);
            }
        }
        for(int  i =0; i  < Numarray.length ; i++){
            top = new Node(Numarray[i], top);
        }
        return top;
    }

    public static int[] aggiungiSpazioInizio(int[] old){
        int[] nuovo = new int[old.length+1];
        int j = nuovo.length-1;
        for(int i = old.length-1; i >= 0; i--){
            nuovo[j] = old[i];
            j -= 1;
        }
        return nuovo;
    }

    public static Node ReverseRic(Node p){
        Node p2 = p;
        if(p2 == null){
            return null;
        }
        else{
            return new Node(p2.getElem(), ReverseRic(p2.getNext()));
        }
        
    }

    public static Node zipSum(Node p, Node q){
        Node top = null;
        Node p2 = p;
        Node q2 = q;
        while(p2 != null && q2 != null){
            top = new Node(p2.getElem() + q2.getElem(),top);
            p2 = p2.getNext();
            q2 = q2.getNext();
        }

        return top;
    }

    public static Node zipSumRic(Node p, Node q){
        Node p2 = p;
        Node q2 = q;
        if(p2 == null && q2 == null){
            return null;
        }
        else{
            return new Node(p2.getElem() + q2.getElem(), zipSumRic(p2.getNext(), q2.getNext()));
        }
        
    }

}
