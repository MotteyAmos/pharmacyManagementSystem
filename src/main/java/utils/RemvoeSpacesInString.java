package utils;

public class RemvoeSpacesInString {

    public static String remove(String str){
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()){
            if (c != ','){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
