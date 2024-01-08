import java.util.Scanner;
public class LeggiArray{
	public static void main(String[] args){
		stampaArrayIntWrap(leggiArrayInt());
	}
	public static int[] leggiArrayInt(){
		return leggiArrayIntRic(0);
	}
	public static int[] leggiArrayIntRic(int i){
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		if(a != 0){
			int[] array = leggiArrayIntRic(i+1);
			array[i] = a;
			return array;
		}
		else{
			return new int[i];
		}
		
	}
	
	public static void stampaArrayIntWrap(int[] a){
		stampaArrayIntRic(a, 0);
	}
	public static void stampaArrayIntRic(int[]a, int i){
		if(i < a.length){
			System.out.println(a[i]);
			stampaArrayIntRic(a, i+1);
		}
	}
}