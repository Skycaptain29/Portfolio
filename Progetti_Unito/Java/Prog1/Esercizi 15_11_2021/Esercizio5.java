public class Esercizio5{
	public static void main(String[] args){
		System.out.println(ProdottoMultipli(1,5,2));
		StampaIntRovescio(5,-1);
	}
	public static int ProdottoMultipli(int n, int m, int q){
		if(n == m){
			return 1;
		}
		if(m % q == 0){
			return m * ProdottoMultipli(n, m-1,q);
		}
		else{
			return 1 * ProdottoMultipli(n,m-1,q);
		}
	}
	public static int StampaIntRovescio(int n, int i){
		if(n == i){
			return n;
		}
		System.out.println(StampaIntRovescio(n,i+1));
		return i;
	}
}