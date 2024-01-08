import java.util.Scanner;
public class Esercizio4{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		//System.out.println("Inserire il numero di elementi previsti: ");
		//int n = input.nextInt();
		int[] pari = new int[100];
		int[] dispari = new int[100];
		int number;
		int i = 0;
		int j = 0;
		boolean exit = false;
		while(!exit){
			number = input.nextInt();
			if(number == 0){
				exit = true;
			}
			if(number%2 == 0){
				pari[i] = number;
				i++;
			}
			else{
				dispari[j] = number;
				j++;
			}
			
		}
		
		
		if(pari[0] == 0 && dispari[0] == 0){
			System.out.println("Non hai inserito numeri");
		}
		else{
			if(pari[0] == 0){
				System.out.println("Tutti i numeri inseriti sono dispari");
			}
			else{
				if(dispari[0] == 0){
					System.out.println("Tutti i numeri inseriti sono pari");
					
				}
				else{
					System.out.println("Ci sono sia numeri pari che dispari");
				}
			}
		}
		
	}
}