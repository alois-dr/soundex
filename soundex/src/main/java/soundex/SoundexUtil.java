package soundex;

import jakarta.validation.constraints.NotNull;

import java.security.InvalidParameterException;
import java.text.Normalizer;
import java.util.*;

public class SoundexUtil {
    private static final String[] chars = new String[]{"A", "E", "H", "I", "O", "U", "W", "Y"};
    private SoundexUtil(){}
    public static String prepare(@NotNull final String word) throws EmptyWordException {
        if(word == null) throw new InvalidParameterException();
        if(word.isEmpty()) throw new EmptyWordException();

        return Normalizer.normalize(word, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replace(" ", "")
                .replaceAll("[-']", "")
                .toUpperCase();
    }
    public static @NotNull String removeDuplicates(@NotNull final String str){
        if(str == null) throw new InvalidParameterException();
        Set<String> hash = new HashSet<>(List.of(str.split("")));
        return String.join("", hash);
    }
    public static @NotNull String getFirstThreeOrZeros(@NotNull final String str){
        if(str == null) throw new InvalidParameterException();
        List<String> list = new ArrayList<>(List.of("0", "0", "0"));
        String[] arr = str.split("");
        for (int i = 0; i < arr.length; i++) {
            if(!arr[i].isEmpty()){
                list.add(i, arr[i]);
            }
        }

        return String.join("", list.subList(0, 3));
    }

    public static String[] getChars(){
        return chars;
    }
}
