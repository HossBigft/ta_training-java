package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {

        if(color==null){
            return  false;
        }
        Pattern colorMatch = Pattern.compile("^#([\\dA-F]{3}){1,2}", Pattern.CASE_INSENSITIVE);
        Matcher m = colorMatch.matcher(color);
        return m.matches();
    }
}





