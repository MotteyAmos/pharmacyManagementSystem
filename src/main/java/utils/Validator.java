package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

    public static boolean emailValidator(String email){
        String emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }
}
