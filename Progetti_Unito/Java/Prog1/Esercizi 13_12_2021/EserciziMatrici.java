import java.util.Scanner;
public class EserciziMatrici{
	public static void main(String[] args){
		int[] a1 = {3, 5, 7}, a2 = {2, 10, 8, 9}, a3 = {8};
		int[][] m1 = initAlt(a1, a2, 6), m2 = initAlt(a3, null, 5);
        int[][] m3 = initAlt(null, a2, 4);
		int[][] m4 = { {2,5,6}, {3,7,8,9,1}, {0,2}, {0,3,9,1} };
		int[] a4 = { 3, 5, 3, 2, 3, 6, 3, 2, 3, 3, 8, 3}; 
		System.out.println("Matrice 1");
		toString(m1);
		System.out.println("Lunghezza di tutte le righe: " + contaElementi(m1));
		System.out.print("Matrice linearizzata: ");
		StampaArray(linearizzaMatrice(m1));
		System.out.println("Lunghezza riga massima: " + maxRowLen(m1));
		System.out.println("Somma righe: ");
		StampaArray(SommaRighe(m1));
		System.out.println();
		
		System.out.println("Matrice 2");
		toString(m2);
		System.out.println("Lunghezza di tutte le righe: " + contaElementi(m2));
		System.out.print("Matrice linearizzata: ");
		StampaArray(linearizzaMatrice(m2));
		System.out.println("Lunghezza riga massima: " + maxRowLen(m2));
		System.out.println("Somma righe: ");
		StampaArray(SommaRighe(m2));
		System.out.println();
		
		System.out.println("Matrice 3");
		toString(m3);
		System.out.println("Lunghezza di tutte le righe: " + contaElementi(m3));
		System.out.print("Matrice linearizzata: ");
		StampaArray(linearizzaMatrice(m3));
		System.out.println("Lunghezza riga massima: " + maxRowLen(m3));
		System.out.println("Somma righe: ");
		StampaArray(SommaRighe(m3));
		System.out.println();
		
		System.out.println("Matrice 4");
		toString(m4);
		System.out.println("Lunghezza di tutte le righe: " + contaElementi(m4));
		System.out.print("Matrice linearizzata: ");
		StampaArray(linearizzaMatrice(m4));
		System.out.println("Lunghezza riga massima: " + maxRowLen(m4));
		System.out.println("Somma righe: ");
		StampaArray(SommaRighe(m4));
		System.out.println();
		
		System.out.println("Occorrenze di 3 in a4: " + conteggio(a4,3));
		
		System.out.println("Occorrenze di 2 in a4: " + conteggio(a4,2));
		
	}
	public static int[][] initAlt(int[] matp, int[] matd, int numr){
		int[][] arr = new int[numr][];
		for(int i = 0; i < numr; i++){
			if(i%2 == 0){
				arr[i] = matp;
			}
			else{
				arr[i] = matd;
			}
		}
		return arr;
	}
	public static void toString(int[][] m){
		if(m != null){
			for(int i = 0; i < m.length;i++){
				if(m[i] != null){
					System.out.print("{ ");
					for(int j = 0; j < m[i].length; j++){
						System.out.print(m[i][j] + " ");
					}
					System.out.println(" }");
				}
				else{
					System.out.println("NULL");
				}
			}
		}
	}
	public static int contaElementi(int[][] m){
		int length_m = 0;
		if(m != null){
			for(int i =0; i < m.length; i++){
				if(m[i] != null){
					length_m += m[i].length;
				}
			}
		}
		return length_m;
	}
	public static int[] linearizzaMatrice(int[][] matrix){
		int[] lineare = new int[contaElementi(matrix)];
		int counter = 0;
		if(matrix != null){
			for(int i = 0; i < matrix.length; i++){
				if(matrix[i] != null){
					for(int j = 0; j < matrix[i].length; j++){
						lineare[counter] = matrix[i][j];
						counter++;
					}
				}
			}
		}
		else{
			lineare = null;
		}
		return lineare;
	}
	public static void StampaArray(int[] arr){
		if(arr != null){
			for(int i = 0; i < arr.length; i++){
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		else{
			System.out.println("Null");
		}
		
	}
	public static int maxRowLen(int[][] m){
		int max = 0;
		if(m != null){
			for(int i = 0; i < m.length; i++){
				if(m[i] != null && m[i].length > max){
					max = m[i].length;
				}
			}
		}
		else{
			max = 0;
		}
		return max;
	}
	public static int[] SommaRighe(int[][] a){
		int[] somme = new int[a.length]; 
		if(a != null){
			for(int i = 0; i < a.length; i++){
				if(a[i] != null){
					for(int j = 0; j < a[i].length; j++){
						somme[i] += a[i][j];
					}
				}
			}
		}
		else{
			somme = null;
		}
		return somme;
	}
	
	public static int[][] azzeraColonnaMax(int[][] mat){
		int[] somma = SommaRighe(mat);
		int max = 0;
		if(somma != null){
			for(int i = 0; i < somma.length; i++){
				if(somma[i] > max){
					max = somma[i];
				}
			}
		}
		if(mat != null){
			for(int i = 0; i < mat.length; i++){
				if(mat[i] != null && i == max){
					for(int j = 0; j < mat[i].length; j++){
						mat[i][j] = 0;
					}
				}
			}
		}
		return mat;
	}
	
	public static boolean domMat(int[][] m){
		boolean a = false;
		return a;
	}
	public static boolean domRiga(int[] r, int j){
		boolean flag = false;
		for(int i = 0; i < r.length; i++){
			if(r[i]%j == 0){
				flag = true;
			}
			else{
				
			}
		}
		return flag;
	}
	
	public static int conteggio(int[] a, int k){
		return conteggioDicotomico(a,k,0,a.length-1);
	}
	public static int conteggioDicotomico(int[] a, int k, int start, int end){
		int mid = (start + end)/ 2;
		if(start == end){
			if(a[start] == k){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			return conteggioDicotomico(a, k, start, mid) + conteggioDicotomico(a, k, mid+1, end);
		}
	}
}