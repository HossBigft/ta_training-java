package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {

        if(email==null){
            return  false;
        }
        Pattern colorMatch = Pattern.compile("^[^_]\\w+_\\w+\\d?@epam\\.com");
        Matcher m = colorMatch.matcher(email);
        return m.matches();

    }
}





