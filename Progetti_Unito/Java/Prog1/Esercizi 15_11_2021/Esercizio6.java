import java.util.Scanner;
public class Esercizio6{
	public static void main(String[] args){
		System.out.println(MassimoInteroRicorsivo(Integer.MIN_VALUE));
	}
	public static int MassimoInteroRicorsivo(int max){
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		if(n.length() != 0 && n > max){
			max = n;
		}
		else if(n.length() == 0){
			return max;
		}
		return MassimoInteroRicorsivo(max);
	}
}