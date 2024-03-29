public class Es_1_4{
    public static void main(String[] args){
        System.out.println(scan(args[0]) ? "OK": "Errore");
    }
    public static boolean scan(String s){
        int state = 0;
        int i = 0;
        while(state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0:
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 1;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 2;
                    }
                    else if(ch == ' '){
                        state = 0;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 1:// NODO DISPARI
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 1;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 2;
                    }
                    else if((ch >= 'L' && ch <= 'Z')){
                        state = 4;
                    }
                    else if((ch == ' ')){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 2: //NODO PARI
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 1;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 2;
                    }
                    else if((ch >= 'A' && ch <= 'K')){
                        state = 5;
                    }
                    else if((ch == ' ')){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 3: //NODO DI BUFFER PER GLI SPAZI
                    if(ch == ' '){
                        state = 3;
                    }
                    else if((ch >= 'A' && ch <= 'K')){
                        state = 5;
                    }
                    else if((ch >= 'L' && ch <= 'Z')){
                        state = 4;
                    }
                    else{
                        state = -1;
                    }
                break;
                
                case 4: //PRIMA LETTERA TRA 'L' E 'Z'
                    if((ch >= 'a' && ch <= 'z')){
                        state = 4;
                    }
                    else if(ch == ' '){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 5://PRIMA LETTERA TRA 'A' E 'K' 
                    if((ch >= 'a' && ch <= 'z')){
                        state = 5;
                    }
                    else if(ch == ' '){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 6://CASO CON SPAZIO DOPO LETTERE
                    if(ch == ' '){
                        state = 6;
                    }
                    else if((ch >= 'A' && ch <= 'K')){
                        state = 5;
                    }
                    else if((ch >= 'L' && ch <= 'Z')){
                        state = 4;
                    }
                    else{
                        state = -1;
                    }
                break;
            }
        }
        return state == 4 || state == 5 || state == 6;
    }
}
