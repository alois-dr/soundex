package soundex;

import jakarta.validation.constraints.NotNull;

public class FrSoundex implements Soundex {
    protected static final String NO_RESULT = "Aucun résultat pour: ";

    @Override
    public String generate(@NotNull String word) throws EmptyWordException {
        String w = SoundexUtil.prepare(word);
        if(w.length() == 1){
            return w + "000";
        }
        w = w.replace("GUI", "KI")
        .replace("GUE", "KE")
        .replace("GA", "KA")
        .replace("GO", "KO")
        .replace("GU", "K")
        .replace("CA", "KA")
        .replace("CO", "KO")
        .replace("CU", "KU")
        .replace("Q", "K")
        .replace("CC", "K")
        .replace("CK", "K");

        w = w.replace("E", "A")
                .replace("I", "A")
                .replace("O", "A")
                .replace("U", "A");

        w = w.replaceAll("^MAC", "MCC")
                .replaceAll("^SCH", "SSS")
                .replaceAll("^ASA", "AZA")
                .replaceAll("^KN", "NN")
                .replaceAll("^PH", "FF")
                .replaceAll("^PF", "FF");

        w = w.replaceAll("([^SC])H", "$1")
                .replaceAll("([^A])Y", "$1")
                .replaceAll("(.*)[ADTS]$", "$1");

        if(w.isEmpty()) throw new EmptyWordException(NO_RESULT + w);

        char first = w.charAt(0);
        w = w.substring(1)
                .replace("A", "");

        String rest = SoundexUtil.getFirstThreeOrZeros(SoundexUtil.removeDuplicates(w));

        return first + rest;
    }
}
