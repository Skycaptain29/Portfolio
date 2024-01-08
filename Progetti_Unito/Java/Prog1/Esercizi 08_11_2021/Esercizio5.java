import java.util.Scanner;
public class Esercizio5{
	public static void main(String[] args){
		for(int i = 0; i < 100; i++){
			if(verificaprimo(i)){
				System.out.print(i);
			}
		}
		
	}
	public static boolean verificaprimo(int n){
		  int i,m=0,flag=0;      
		  m=n/2;
		   for(i=2;i<=m;i++){      
			if(n%i==0){ 
			 flag=1;      
			 break;      
			}    
		   if(flag==0)
			{ 
				return true; 
			}  
		  }//end of else  
	} 
}