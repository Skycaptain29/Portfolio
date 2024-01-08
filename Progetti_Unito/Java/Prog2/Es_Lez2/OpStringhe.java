public class OpStringhe {
    public static String longest(String[] Sarray){
        assert Sarray.length > 0: "Immettere un array con almeno 1 elemento";
        int maxL = -1;
        String longest = "";
        for(int i = 0; i < Sarray.length; i++){
            if(Sarray[i].length() >= maxL){
                maxL = Sarray[i].length();
                longest = Sarray[i];
            }
        }
        return longest;
    }

    public static String concatAll(String[] Sarray){
        assert Sarray.length > 0: "Immettere un array con almeno 1 elemento";
        String concatS = Sarray[0];
        for(int i = 1; i < Sarray.length; i++){
            concatS = concatS.concat(Sarray[i]);
        }
        return concatS;
    }

    public static String Trim(String input){
        String trimS = input;
        Boolean flag = false;
        Boolean Error = false;
        int i = 0;
        int beginIndex = -1, endIndex = -1;
        while(!flag){
            if(input.charAt(i) != ' '){
                flag = true;
                beginIndex = i;
            }
            ++i;
            if(i == input.length()){
                flag = true;
                Error = true;
            }
        }

        flag = false;
        i = input.length()-1;

        while(!flag){
            if(input.charAt(i) != ' '){
                flag = true;
                endIndex = i;
            }
            --i;
            if(i == -1){
                flag = true;
                Error = true;
            }
        }
        if(!Error){
            trimS = input.substring(beginIndex, endIndex+1);
        }
        else{
            trimS = "";
        }
        return trimS;
    }
}
