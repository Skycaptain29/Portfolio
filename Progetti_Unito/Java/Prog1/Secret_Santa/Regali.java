
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



class Regali{

    public static void main(String args[]) {
        ShuffleAndSend();
    }
	public static List<String> insertGuests(){
		//System.out.println("Inserire il numero di ospiti: ");
		Scanner input = new Scanner(System.in);
		//int number = input.nextInt();
		String name;
		
		List<String> guests = new ArrayList<>();
		
		/*for(int i = 0; i < number; i++){
			System.out.println("Inserire l'ospite " + i+1 + " : ");
			name = input.next();
			guests.add(name);
		}*/
		System.out.println("Inserisci il nome del file");
		name = input.next();
		
		try {
		  File myObj = new File(name);
		  Scanner myReader = new Scanner(myObj);
		  while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			guests.add(data);
		  }
		  myReader.close();
		} catch (FileNotFoundException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
		return guests;
	}
	public static void ShuffleAndSend(){
		boolean flag = false;
		Scanner input = new Scanner(System.in);
		while(!flag){
			List<String> guests = insertGuests();
			List<Integer> guests_Numbers = new ArrayList<>();
			for (int i = 0; i < guests.size();i++) {
				guests_Numbers.add(i);
			}
			boolean shuffled = false;
			outer:
			while (!shuffled) {
				Collections.shuffle(guests_Numbers);
				shuffled = true;
				for (int j = 0; j < guests_Numbers.size(); j++) {
					if (j == guests_Numbers.get(j)) {
						shuffled = false;
						continue outer;
					}
				}
			}
			try{
				PrintWriter printWriter = new PrintWriter("Coppie.txt");
				for (int j = 0; j < guests.size(); j++){
					printWriter.println(guests.get(j) + " gives a gift to -> " + guests.get(guests_Numbers.get(j)) + "\n   ");				
					System.out.println(guests.get(j) + " gives a gift to -> " + guests.get(guests_Numbers.get(j)) + "\n");
				}
				printWriter.close();
			}
			catch(FileNotFoundException err){

			}
			
				
			
			System.out.println("Confermi gli abbinamenti? y/n");
			String response = input.next();
			if(response.equals("y")){
				System.out.println("Inserisci email da cui inviare:");
				String Username = input.next();
				System.out.println("Inserisci password:");
				String Password = input.next();
				System.out.println("Inserisci numero revisione:");
				int Rev = input.nextInt();
				Email.SendEmail(guests, guests_Numbers, Username, Password, Rev);
				flag = true;
			}
			
		}

		
	}
}