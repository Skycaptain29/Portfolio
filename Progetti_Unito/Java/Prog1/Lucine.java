public class Lucine{
	public static void main(String[] args){
		Boolean[] accesa = new Boolean[10];
		char[] colore = {'R','G','B','V','B','R','G','V','R','R'};
		for(int  i = 0; i < accesa.length; i++){
			accesa[i] = false;
		}
		accesa = LampadinaSiNoWrapper(accesa);
		StampaArray(accesa);
		System.out.println(ContaLampadineSpenteWrapper(accesa));
		AccendiRosseWrapper(accesa, colore);
		StampaArray(accesa);
		
	}
	public static Boolean[] LampadinaSiNoWrapper(Boolean[] accesa){
		return LampadinaSiNoControVariante(accesa,0);
	}
	public static Boolean[] LampadinaSiNoControVariante(Boolean[] accesa, int i){
		if(i == accesa.length){
			return accesa;
		}
		else{
			if(i%2 == 0){
				accesa[i] = false;
			}
			else{
				accesa[i] = true;
			}
			return LampadinaSiNoControVariante(accesa, i+1);
		}
	}
	
	public static int ContaLampadineSpenteWrapper(Boolean[] accesa){
		return ContaLampadineSpenteCoVariante(accesa, accesa.length-1);
	}
	
	public static int ContaLampadineSpenteCoVariante(Boolean[] accesa, int i){
		if(i == 0){
			if(accesa[i] == false){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			if(accesa[i] == false){
				return 1 + ContaLampadineSpenteCoVariante(accesa, i-1);
			}
			else{
				return 0 + ContaLampadineSpenteCoVariante(accesa, i-1);
			}
			
		}
	}
	
	public static int AccendiRosseWrapper(Boolean[] accesa, char[] colore){
		return AccendiRosseDicotomico(accesa, colore, 0, accesa.length-1);
	}
	
	public static int AccendiRosseDicotomico(Boolean[] accesa, char[] colore, int inizio, int fine){
		if(inizio == fine){
			if(accesa[inizio] == false && colore[inizio] == 'R'){
				accesa[inizio] = true;
			}
			return 0;
		}
		else{
			int media = (inizio + fine) / 2;
			return AccendiRosseDicotomico(accesa, colore, inizio, media) + AccendiRosseDicotomico(accesa, colore, media+1, fine);
		}
	}
	
	public static void StampaArray(Boolean[] arr){
		if(arr != null){
			for(int i = 0; i < arr.length; i++){
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		else{
			System.out.println("Null");
		}
		
	}
}