import java.util.Scanner;
public class MetodiSuArray{
	public static int[] initArrayInt(){
		Scanner input = new Scanner(System.in);
		int grandezza = input.nextInt();
		int[] array = new int[grandezza];
		for(int i = 0; i < grandezza; i++){
			array[i] = input.nextInt();
		}
		return array;
	}
	public static void stampaArrayInt(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	public static void copiaElementi(int[] from, int[] to){
		for(int i = 0; i < from.length; i++){
			to[i] = from[i];
		}
	}
	public static int[] clonaArray(int[] a){
		int[] arrayClone = new int[a.length];
		copiaElementi(a, arrayClone);
		return arrayClone;
	}
	public static int[] filtroMinoriDi(int[] a, int limiteSuperiore){
		int counter = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] < limiteSuperiore)
			{
				counter++;
			}
		}
		int[] lesserThan = new int[counter];
		int j = 0;
		for(int i = 0; i < counter; i++){
			if(a[i] < limiteSuperiore)
			{
				lesserThan[j] = a[i];
				j++;
			}
		}
		return lesserThan;
	}
	public static int[] filtroIntervalloDisp(int[] a, int min, int max) {
		int counter = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] % 2 != 0 && a[i] >= min && a[i] <= max)
			{
				counter++;
			}
		}
		int[] interval = new int[counter];
		int j = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] % 2 != 0 && a[i] >= min && a[i] <= max)
			{
				interval[j] = a[i];
				j++;
			}
		}
		return interval;
	}
	public static boolean[] trasduttore(int[] a, int limiteSuperiore){
		boolean[] checker = new boolean[a.length];
		for(int i = 0; i < a.length; i++){
			if(a[i] <= limiteSuperiore){
				checker[i] = true;
			}
			else{
				checker[i] = false;
			}
		}
		return checker;
	}
	public static void stampaArrayBoolean(boolean[] a){
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	public static boolean eqArray(int[] a, int[] b){
		if(a == null && b == null){
			return true;
		}
		else{
			if(a != null && b != null){
				if(a.length != b.length){
				return false;
				}
				if(a.length == b.length){
					for(int i = 0; i < a.length; i++){
						if(a[i] != b[i])
							return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	public static boolean tuttiPariMaggioriDi(int[] a, int lowerLimit){
		if(a == null){
			return false;
		}
		else{
			for(int i = 0; i < a.length; i++){
				if(a[i] <= lowerLimit || a[i] % 2 != 0)
					return false;
			}
		}
		
		return true;
	}
}