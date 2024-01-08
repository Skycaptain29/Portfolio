import java.util.Scanner;
public class Esercizio4{
	public static void main(String[] args){
		Pitagora();
	}
	public static void Pitagora(){
		for(int i = 1; i <= 10; i++){
			for(int j = 1; j <= 10; j++){
				System.out.print((i*j)+"\t");
			}
			System.out.println();
		}
	}
}