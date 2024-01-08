import java.util.Scanner;
public class Esercizio9{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int Input = input.nextInt();
		int n = 2*Input-1;
		
		for(int i = 0; i < n/2; i++){
			for(int j = 0; j < i; j++){
				System.out.print(".");
			} 
			System.out.println("**");
		}
		for(int j = 0; j < Input-1; j++){
			System.out.print(".");
		}
		System.out.println("**");
		
		for(int i = n/2; i > 0; i--){
			for(int j = i; j > 1; j--){
				System.out.print(".");
			} 
			System.out.println("**");
		}
	}
}	