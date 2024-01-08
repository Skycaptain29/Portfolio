import java.util.Scanner;
public class Esercizio3{
	public static void main(String[] args){
		System.out.println(somma(5));
		
	}
	public static int somma(int n){
		int sommatot = 0;
		for(int i=n; i > 0; i--){
			sommatot += numeroNcifre(i);
		}
		return sommatot;
	}
	public static int numeroNcifre(int n){
		int numero = n;
		for(int i = 1; i < n; i++){
			numero = 10*numero+n;
		}
		 return numero;
	}
}