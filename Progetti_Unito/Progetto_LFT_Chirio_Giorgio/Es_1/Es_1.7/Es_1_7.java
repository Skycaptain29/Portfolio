public class Es_1_7{
    public static void main(String[] args){
        System.out.println(scan(args[0])? "Ok": "No");
    }

    public static boolean scan(String s){
        int state = 0, i = 0;
        while(state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0:
                    if(ch == 'G' || ch == 'g'){
                        state = 8;
                    }
                    else{
                        state = 1;
                    }
                break;

                case 1:
                    if(ch == 'I' || ch == 'i'){
                        state = 2;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 2:
                    if(ch == 'O' || ch == 'o'){
                        state = 3;
                    }
                    else{
                        state = -1;
                    }   
                break;

                case 3:
                    if(ch == 'R' || ch == 'r'){
                        state = 4;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 4:
                    if(ch == 'G' || ch == 'g'){
                        state = 5;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 5:
                    if(ch == 'I' || ch == 'i'){
                        state = 6;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 6:
                    if(ch == 'O' || ch == 'o'){
                        state = 7;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 7:
                    state = -1;
                break;

                case 8:
                    if(ch == 'I' || ch == 'i'){
                        state = 9;
                    }
                    else{
                        state = 2;
                    }
                break;

                case 9:
                    if(ch == 'O' || ch == 'o'){
                        state = 10;
                    }
                    else{
                        state = 3;
                    }
                break;

                case 10:
                    if(ch == 'R' || ch == 'r'){
                        state = 11;
                    }
                    else{
                        state = 4;
                    }
                break;

                case 11:
                    if(ch == 'G' || ch == 'g'){
                        state = 12;
                    }
                    else{
                        state = 5;
                    }
                break;

                case 12:
                    if(ch == 'I' || ch == 'i'){
                        state = 13;
                    }
                    else{
                        state = 6;
                    }
                break;

                case 13:
                    state = 7;
                break;
            }
        }
        return state == 7;
    }
}
