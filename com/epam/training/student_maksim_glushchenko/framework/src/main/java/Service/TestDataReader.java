package Service;

import java.util.ResourceBundle;

public class TestDataReader {
    private  static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));
    public static Integer getTestData(String key){ return  Integer.valueOf(resourceBundle.getString(key));}
}
