import java.util.Scanner;
public class Esercizio6{
	public static void main(String[] main){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(i == j){
					System.out.print("\\");
				}
				if(j > i){
					System.out.print(":");
				}
				if(j < i){
				{
					System.out.print("*");
				}
				}
			}
			System.out.println();
		}
	}
}