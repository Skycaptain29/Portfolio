public class Prova{
		public static boolean e1(int[][] x) {
		boolean esiste = false;
		for (int i = 0; i  < x.length ; i++)
			for (int j = 0; j < x[i].length; j++)
				esiste = (x[i][j]==0);
		return esiste;
	}
	public static boolean e2(int[][] x) {
    return e2R(x, 0, x.length-1);
	}

	public static boolean e2R(int[][] x, int l, int r) {
		if (l == r) {
			return esiste(x[l]);
		} else {
			int m = (l+r)/2;
			return  e2R(x, l, m) || e2R(x, m+1, r);
		}
	}

	public static boolean esiste(int[] r) {
		boolean trovato = false;
		for(int i=0; i < r.length; i++){
			if(r[i] == 0){
				trovato = true;
			}
		}
		return trovato;
	}
	
	
	public static void dPosDValD(int[]a, int l, int r, int[] ris){
		if(l==r && r%2!=0){
			
		}
		else{
			int m = (l+r)/2;
			dPosDValD(a,l,m,ris);
			dPosDValD(a,m+1,r,ris);
		}
	}
	
	public static void main(String[] args){
		  int[][] a0 = { {} }; //false
 int[][] a1 = { {1} }; //false
 int[][] a2 = { {1},{1} }; //false
 int[][] a3 = { {1},{0}  }; //true
 int[][] a4 = { {1,2} ,{1,0} }; //true
 int[][] a5 = { {1,2}, {1,2} }; //false
 int[][] a6 = { {1,2,3},{1,3,4},{1,0,2} }; // true
 int[][] a7 = { {1,2,2},{1,3,4},{1,2,3} }; // false
   int[] input8   = {1,1,1};
		
		System.out.println(e2(a0));
		System.out.println(e2(a1));
		System.out.println(e2(a2));
		System.out.println(e2(a3));
		System.out.println(e2(a4));
		System.out.println(e2(a5));
		System.out.println(e2(a6));
		System.out.println(e2(a7));
		System.out.println(contaDispari(input8,0,input8.length-1)-1);
		
	}
	
	
	
	 /*public static int[] fMin(int[] a, int m, int i, int[] r) {
        if(i == a.lenght){
            return r.lenght-1;
        }else{
			int indice = fMin(a,m,i+1,r);
            if(a[i]<m){
                r[indice] = a[i];
				return indice-1;
            }else{
                return indice;
            }
        }
    }
	
	public static int[] fMin(int[] a, int m, int i, int[] r) {
        if(i == 0){
            return 0;
        }else{
			int indice = fMin(a,m,i-1,r);
            if(a[i]<m){
                r[indice] = a[i];
				return indice+1;
            }else{
                return indice;
            }
        }
    }
	
	public static int[] fMin(int[] a, int m, int l, int r, int[] r) {
        if(l == r){
			if(a[i]<m){
                int[] r= new int[1];
				r[0] = a[i];
				return r;
            }else{
                return new int[0];
            }
        }else{
			int m = (l+r)/2;
            if(a[i]<m){
                int[] r= new int[1];
				r[0] = a[i];
				return r;
            }else{
                return new int[0];
            }
			int[] arrayR = fmin("DX");
			int[] arrayL = fmin("SX");
			merge(arrayL,arrayR);
        }
    }*/
	
	public static int contaDispari(int[] a, int l, int r) {
        if(l == r){
            if(a[l]%2 != 0){
                return 1;
            }else{
                return 0;
            }
        }else{
            int m = (l+r)/2;
            return contaDispari(a,l,m) + contaDispari(a,m+1,r);
        }
    }
}