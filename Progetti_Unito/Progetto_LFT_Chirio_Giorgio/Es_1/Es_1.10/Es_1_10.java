public class Es_1_10{
    public static void main(String[] args){
        System.out.println(scan(args[0])? "Ok": "No");
    }

    public static boolean scan(String s){
        int state = 0, i = 0;
        while(state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0:
                   if(ch == '/'){
                    state = 1;
                   }
                   else if(ch == 'a' || ch == '*'){
                    state = 0;
                   }
                   else{
                    state = -1;
                   }
                break;
                   
                case 1:
                   if(ch == '*'){
                    state = 2;
                   }
                   else if(ch == 'a'){
                    state = 0;
                   }
                   else{
                    state = -1;
                   }
                break;

                case 2:
                   if(ch == 'a' || ch == '/'){
                    state = 2;
                   }
                   else if(ch == '*'){
                    state = 3;
                   }
                   else{
                    
                    state = -1;
                   }
                break;

                case 3:
                   if(ch == 'a'){
                    state = 2;
                   }
                   else if(ch == '/'){
                    state = 4;
                   }
                   else if(ch == '*'){
                    state = 3;
                   }
                   else{
                    state = -1;
                   }
                break;

                case 4:
                    if(ch == 'a' || ch == '*'){
                        state = 4;
                    }
                    else if(ch == '/'){
                        state = 5;
                    }
                    else{
                        state = -1;
                    }
                break;

                case 5:
                    if(ch == '*'){
                    state = 2;
                   }
                   else if(ch == 'a'){
                    state = 4;
                   }
                   else if(ch == '/'){
                    state = 5;
                   }
                   else{
                    state = -1;
                   }
                break;
            }
        }
        return state == 4 || state == 0;
    }
}


