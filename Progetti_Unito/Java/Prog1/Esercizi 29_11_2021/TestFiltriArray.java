import java.util.Scanner;
public class TestFiltriArray{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int[] array = MetodiSuArray.initArrayInt();
		int upperLimit = input.nextInt();
		System.out.println("I numeri minori di: " + upperLimit + " sono: ");
		MetodiSuArray.stampaArrayInt(MetodiSuArray.filtroMinoriDi(array,upperLimit));
		
		int lowerLimit = input.nextInt();
		upperLimit = input.nextInt();
		System.out.println("I numeri dispari compresi tra: " + lowerLimit + " e "+ upperLimit + " sono: ");
		MetodiSuArray.stampaArrayInt(MetodiSuArray.filtroIntervalloDisp(array,lowerLimit,upperLimit));
		
		upperLimit = input.nextInt();
		System.out.println("Controllo buleano con numeri inferiori a : "+ upperLimit);
		MetodiSuArray.stampaArrayBoolean(MetodiSuArray.trasduttore(array,upperLimit));
	}
}