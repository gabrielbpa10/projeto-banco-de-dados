package Utils;

import java.util.ArrayList;

public class StringUtil {

    public static <T> String toString (ArrayList<T> arr) {
        String a = "[";
        for (int i = 0; i < arr.size(); i++) {
            a += arr.get(i);
            if (i < arr.size() - 1) {
                a += ", ";
            }
        }
        return a + "]";
    }
    
}
