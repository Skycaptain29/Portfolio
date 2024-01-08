import java.util.Scanner;
public class Esercizio3{
	public static void main(String[] args){
		System.out.println(StringaMax());
	}
	public static String StringaMax(){
		Scanner input = new Scanner(System.in);
		String n = input.nextLine();
		int lnt_max = Integer.MIN_VALUE;
		String string_max = "";
		while(n != ""){
			if(n.length() > lnt_max){
				lnt_max = n.length();
				string_max = n;
			}
			n = input.nextLine();
		}
		return string_max;
	}
}