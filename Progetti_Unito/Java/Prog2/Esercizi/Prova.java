class Prova{
    public static Node rev(Node p, Node q)
    {
        assert p != null && q != null: "Liste nulle";
        Node tmp = p;
        int lenght = 0;
        while(tmp != null){
            lenght++;
            tmp = tmp.getNext();
        }
        int[] listP = new int[lenght];
        int i = 0;
        while(tmp != null){
            listP[i] = tmp.getElem();
            i++;
            tmp = tmp.getNext();
        }
        Node newList = null;
        for(int j = listP.length-1; j >= 0; j++){
            newList.setNext(new Node(listP[j], newList));
        }
        newList.setNext(q);
        return newList;

    }
  
   //p={1,2,3}
   static Node p = new Node(1,new Node(2,new Node(3,null)));
   //q={10,20,30}
   static Node q = new Node(10,new Node(20,new Node(30,null)));
   
   public static String toString(Node p)
   {if (p==null) 
     return ""; 
    else 
     return p.getElem() +" " +  toString(p.getNext());
   }
  
   public static void check(String msg, String a, String b)
    {if (a.equals(b))
      System.out.println(msg + a + " OK");
     else
      System.out.println(msg + a + " INSTEAD OF " + b);
    }
  
  public static void main(String[] args)
   {check("rev(p,q)=", toString(rev(p,q)), "3 2 1 10 20 30 ");
   }
  
  }




class Node { private int elem; private Node next;
    public Node(int elem, Node next){this.elem=elem;this.next=next;}
    public int getElem(){return elem;}
    public Node getNext(){return next;}
    public void setElem(int elem){this.elem=elem;}
    public void setNext(Node next){this.next=next;}}
  
