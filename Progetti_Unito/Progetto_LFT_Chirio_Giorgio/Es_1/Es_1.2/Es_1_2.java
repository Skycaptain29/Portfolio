public class Es_1_2{

    public static void main(String[] args) {
        System.out.println(dfa(args[0]) ? "ok" : "Errore");
    }

    public static Boolean dfa(String s){
        int Stato = 0;
        int i = 0;
        while(Stato >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(Stato){
                case 0:
                    if((ch >= 'A' && ch <= 'Z' )|| (ch >= 'a' && ch <= 'z')){
                        Stato = 1;
                    }
                    else if(ch >= '0' && ch <='9'){
                        Stato = 2;
                    }
                    else if(ch == '_'){
                        Stato = 3;
                    }
                    else{
                        Stato = -1;
                    }
                break;
                case 1:
                    if((ch >= 'A' && ch <= 'Z' )|| (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <='9') || ch == '_'){
                        Stato = 1;
                    }
                    else{
                        Stato = -1;
                    }
                break;
                case 2:
                    if((ch >= 'A' && ch <= 'Z' )|| (ch >= 'a' && ch <= 'z') || ch == '_' || ch >= '0' && ch <='9'){
                        Stato = 2;
                    }
                    else{
                        Stato = -1;
                    }
                break;
                case 3:
                    if(ch == '_'){
                        Stato = 3;
                    }
                    else if((ch >= 'A' && ch <= 'Z' )|| (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <='9') || ch == '_'){
                        Stato = 1;
                    }
                    else{
                        Stato = -1;
                    }
                break;
            } 
            
        }
        return Stato == 1;
    }

}
