public class Es_1_5{
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
                    if((ch >= 'A' && ch <= 'K')){
                        state = 1;
                    }
                    else if((ch >= 'L' && ch <= 'Z')){
                        state = 2;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 1:// NODO A-K
                    if((ch >= 'a' && ch <= 'z')){
                        state = 1;
                    }
                    else if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 4;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 2: //NODO L-Z
                    if((ch >= 'a' && ch <= 'z')){
                        state = 2;
                    }
                    else if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 5;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 3:
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 4;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }
                break;
                
                case 4://PRIMA LETTERA TRA 'L' E 'Z'
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 4;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 5://PRIMA LETTERA TRA 'A' E 'K' 
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 5;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 6:
                    if((ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')){
                        state = 5;
                    }
                    else if(ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8'){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;
            }
        }
        return state == 3 || state == 5;
    }
}
