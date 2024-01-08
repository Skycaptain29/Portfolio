import java.util.Scanner;
public class Esercizio2{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int media = 0;
		int Input = 0;
		int i = 0;
		do{
			Input = input.nextInt();
			
			media += Input;
			
			i++;
		}while(Input != 0);
		media = media / (i--);
		System.out.println(media);
	}
}