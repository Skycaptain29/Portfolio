public class Esercizio8{
	public static void main(String[] args){
		StampaRighe(1,1);
	}
	public static int StampaRighe(int i, int j){
		if(i > 10){
			return 1;
		}
		else{
			StampaColonna(i, j);
		}
		StampaRighe(i+1,j);
		return 1;
	}
	public static int StampaColonna(int i, int j){
		if(j > 10){
			System.out.println();
			return 1;
		}
		else{
			System.out.print(i*j + "\t");
			return StampaColonna(i, j+1);
		}
	}
}