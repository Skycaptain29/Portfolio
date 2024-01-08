import java.util.Scanner;
public class MetodiRicorsivisuarray{
	public static void main(String[] args){
		final int[] a0 = {0,1,2,3,4,5,6,7}; // 16
		final int[] a1 = {3,7,9,4,5,12,11}; // 35
		final int[] a2 = null; // 0
		final int[] a3 = {0,10,40,60,20}; // 0
		System.out.println(sommaDispariWrap(a0));
		System.out.println(sommaDispariWrap(a1));
		System.out.println(sommaDispariWrap(a2));
		System.out.println(sommaDispariWrap(a3));
		
		final int[] altezze = {5895, 4810, 6194, 4897, 4884, 8848, 6962};
		final String[] nomi = {
		"Kilimangiaro", "Monte Bianco", "Monte Denali",
		"Massiccio Vinson", "Puncak Jaya", "Everest", "Aconcagua"};
		System.out.println(IndiceMassimoWrap(altezze));
		//System.out.println("Montagna più alta:" + nomi[IndiceMassimoWrap(altezze)] + " altezza: " + altezze[IndiceMassimoWrap(altezze)]);
	}
	public static boolean tuttiPariWrap(int[] a){
		return tuttiPari(a,0);
	}
	public static boolean tuttiPari(int[] a, int i){
		if(i < a.length-1){
			return(a[i]%2 ==0)&& tuttiPari(a,i++);
		}
		else{
			return a[a.length-1]%2==0;
		}
	}
	public static boolean esisteMultiploWrap(int[] a, int m){
		return esisteMultiplo(a, a.length-1, m);
	}
	public static boolean esisteMultiplo(int[] a, int i, int m){
		if(i > 0){
			return (a[i]%m == 0) && esisteMultiplo(a,--i,m);
		}
		else{
			return a[0]%m == 0;
		}
	}
	
	public static int sommaDispariWrap(int[] a){
		if(a != null){
			return sommaDispari(a, 0, a.length-1);
		}
		else{
			return 0;
		}
	}
	
	public static int sommaDispari(int[] a, int i,int j){
		
		if(i<j){
			int med = (i+j)/2;
			return sommaDispari(a, i, med) + sommaDispari(a,med+1,j);
		}
		else{
			if(a[i]%2 != 0){
				
				return a[i];
			}
			else{
			
				return 0;
			}
			
		}
	}
	
	public static int IndiceMassimoWrap(int[] a){
		return IndiceMassimo(a, 0, a.length-1);
	}
	
	public static int IndiceMassimo(int[] a, int i, int j){
		if(i == j){
			return i;
		}
		else{
			int m = i+j/2;
			return Math.max(a[IndiceMassimo(a,i,m)],a[IndiceMassimo(a,m+1,j)]);
		}
	}
}