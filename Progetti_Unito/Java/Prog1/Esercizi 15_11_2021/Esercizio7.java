import java.util.Scanner;
public class Esercizio7{
	public static void main(String[] args){
		System.out.println(StringaMaxRicorsiva(Integer.MIN_VALUE, ""));
	}
	public static String StringaMaxRicorsiva(int max, String string_max){
		Scanner input = new Scanner(System.in);
		String n = input.nextLine();
		if(n != "" && n.length() > max){
			max = n.length();
			string_max = n;
		}
		else if(n.length() == 0){
			return string_max;
		}
		return StringaMaxRicorsiva(max, string_max);
	}
	
	
	public static void mm(int[] a){
		int[] x ={3,2,1,0};
		m(x, x[0],x[1]);
		m(a, x[1], x[2]);
	}
}