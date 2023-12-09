package Transylvania.Validator;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public static void checkIsEmpty(String val){
        if (val.equals("")) {throw new InputMismatchException("all fields must be filled!");}
    }

    public static void checkPhoneNumber(String phone){
        if (!phone.startsWith("08")){
            throw new InputMismatchException("phone number must be started with '08' format!");
        }
        if (!(phone.length() >= 11 && phone.length() <= 13) ){
            throw new InputMismatchException("phone number digit length must be between 11 and 13!");
        }
    }

//    public static void checkValidEmailFormat(String email){
//        if (!email.endsWith("@gmail.com") || !email.endsWith("@yahoo.com") || !email.endsWith("@gmail.co.id")
//                || !email.endsWith("@mail.com") || !email.endsWith("@yahoo.co.id")){
//            throw new InputMismatchException("invalid email");
//        }
//    }

    public static void checkValidEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:yahoo\\.com|yahoo\\.co\\.id|gmail\\.com|mail\\.com|gmail\\.co\\.id)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new InputMismatchException("invalid email format!");
        }
    }

    public static void checkFullNameLength (String name){
        if (name.length() < 2 && name.length() > 20){
            throw new InputMismatchException("full name requires a length of min 3 and max 20!");
        }
    }

    public static void checkNameIsCharacter(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                throw new NumberFormatException("full name must be characters!");
            }
        }
    }
}
