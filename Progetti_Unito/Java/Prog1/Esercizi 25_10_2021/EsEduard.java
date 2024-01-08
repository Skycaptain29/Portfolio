public class EsEduard{
	public static void main(String[] args){
		int n = 5;
        Floyd(n);
	}
	static void Floyd(int n)
    {
		int curr_val = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%2d ", curr_val++);
            }
            System.out.println("");
        }
    }
}