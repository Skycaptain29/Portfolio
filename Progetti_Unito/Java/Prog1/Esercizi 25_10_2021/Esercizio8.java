import java.util.Scanner;
public class Esercizio8{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int Input;
		int[] array = new int[10];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 10; i++){
			array[i] = input.nextInt();
		}
		
		for(int i = 0; i < 10; i++){
			if(array[i] > max){
				max = array[i];
			}
			if(array[i] < min){
				min = array[i];
			}
		}
		System.out.println("Massimo : " + max);
		System.out.println("Minimo : " + min);
	}
}		