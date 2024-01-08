import java.util.Scanner;
public class Esercizio7{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int Input = input.nextInt();
		int n = 0;
		int m = 1;
		int k = 0;
		System.out.println(n);
		System.out.println(m);
		
		for(int i = 0; i < Input-2; i++){
			k = m;
			m += n;
			n = k;
			System.out.println(m);
		}
	}
}		