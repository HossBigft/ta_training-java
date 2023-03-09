package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.regex.*;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if(sample==null || words==null){
            return 0;
        }
        int counter =0;
        for(String word : words){
            String cmpWord= word.trim();
            String cmpSample= sample.trim();
            if(cmpWord.equalsIgnoreCase(cmpSample)){
                counter++;
            }
        }
        return counter;
    }

    public static String[] splitWords(String text) {
        if(text==null || text.isEmpty()){
            return null;
        }
        String trimmed = text.replaceAll("^[ ,\\.;:\\?!]+","").replaceAll("[ ,\\.;:\\?!]+$","");
        Pattern p1 = Pattern.compile("[ ,\\.;:\\?!]+");
        Matcher m = p1.matcher(text);
        if(!m.find()){
            return new String[]{text};
        }
        String splitted[] = p1.split(trimmed,0);
        if(splitted.length==1){
            return null;
        }
        return splitted;
    }

    public static String convertPath(String path, boolean toWin) {
        if(path==null || path.isEmpty()){
            return null;
        }
        String converted = path;
        Pattern invalidCheck = Pattern.compile(".+~|/.*\\\\" +
                "|\\\\.*/" +
                "|(C:).*(C:)" +
                "|:\\\\.+/" +
                "|.+/.+\\\\" +
                "|~\\\\.*/" +
                "|^~.*\\\\"+
                "|\\\\{2,}"+
                "|/{2,}");
        Matcher invalidPath = invalidCheck.matcher(path);

        if(invalidPath.find()){
            return null;
        }

        if(toWin){
           converted= converted.replaceAll("^\\/", "C:\\\\");
           converted= converted.replace("~", "C:\\User");
           converted= converted.replace("/", "\\");
        }else{
            converted=converted.replace("C:\\User", "~");
            converted=converted.replace("C:", "");
            converted=converted.replace("\\","/" );
        }
        return converted;
    }

    public static String joinWords(String[] words) {
        if(words==null){
            return null;
        }
        StringBuilder sb= new StringBuilder();
        for(String word : words){
            if(!word.isEmpty()){
                sb.append(word);
                sb.append(", ");
            }

        }
        if(sb.toString().isEmpty()){
            return null;
        }
        sb.delete(sb.length()-2,sb.length());
        sb.insert(0,"[");
        sb.insert(sb.length(),"]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}