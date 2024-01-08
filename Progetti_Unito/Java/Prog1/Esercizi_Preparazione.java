public class Esercizi_Preparazione{
	public static void main(String[] args){
		int[][] a = {{1,2},{2,1}};
		System.out.println(SommaC(a,0));
		System.out.println(SommaR(a[0]));
		System.out.println(e2(a));
	}
	
	public static boolean e2(int[][] a){
		boolean risultato = (a!=null);
		if(risultato){
			risultato = a.length == 0;
			if(!risultato){
				risultato = e2_Ric(a,0,a.length-1);
			}
		}
		return risultato;
	}
	public static boolean e2_Ric(int[][] a, int inizio, int fine){
		if(inizio == fine){
			return SommaC(a,inizio) == SommaR(a[inizio]);
		}
		else{
			int media = (inizio-fine) / 2;
			return e2_Ric(a, inizio, media) && e2_Ric(a, media+1, fine);
		}
		
	}
	public static int SommaC(int[][] a, int j){
		return SommaC_Ric(a, 0, j);
	}
	public static int SommaC_Ric(int[][] a, int i, int j){
		if(i == a.length-1){
			return a[i][j];
		}
		else{
			return a[i][j] + SommaC_Ric(a,i+1,j);
		}
	}
	public static int SommaR(int[] a){
		return SommaR_Ric(a, a.length-1);
	}
	public static int SommaR_Ric(int[] a, int i){
		if(i == 0){
			return a[i];
		}
		else{
			return a[i] + SommaR_Ric(a,i-1);
		}
	}
}