import java.util.Scanner;
public class Esercizio2{
	public static void main(String[] args){
		System.out.println(MassimoIntero());
	}
	public static int MassimoIntero(){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int max = Integer.MIN_VALUE;
		while(n != 0){
			if(n > max){
				max = n;
			}
			n = input.nextInt();
		}
		return max;
	}
}