import java.util.Scanner;
public class Esercizio1{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String Stringa;
		Stringa = input.nextLine();
		System.out.println(palindroma(Stringa));
	}
	public static String palindroma(String Stringa){
		boolean flag = false;
		for(int i = 0; i < Stringa.length()/2; i++){
			if(Stringa.charAt(i) != Stringa.charAt(Stringa.length()-1-i)){
				flag = true;
			}
			if(flag){
				return "La stringa non e' palindroma";
			}
		}
		return "La stringa e' palindroma";
	}
}