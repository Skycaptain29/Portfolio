import java.util.Scanner;
public class Esercizio5{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int righe = input.nextInt();
		int numero = 1;
		
		for(int i = 1; i <= righe; i++){
			for(int j = 1; j <= i; j++){
				System.out.print((numero++) +" ");
			}
			System.out.println();
		}
	}
}