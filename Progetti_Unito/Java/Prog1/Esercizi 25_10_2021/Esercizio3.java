import java.util.Scanner; 
public class Esercizio3{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("inserire un numero: ");
		int n = input.nextInt();
		for(int i = 1; i < n; i++){
			if(n%i == 0){
				System.out.println(i);
			}
		}
	}
}