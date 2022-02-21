package soundex;

public class BasicEngSoundex implements Soundex{
    @Override
    public String generate(String word) throws EmptyWordException {
        String w = SoundexUtil.prepare(word);
        if(w.length() == 1){
            return w + "000";
        }
        String first = w.substring(0, 1);
        String rest = w.substring(1);
        for (String c : SoundexUtil.getChars()) {
            rest = rest.replace(c, "");
        }

        rest = rest.replaceAll("([BFPV])[BFPV]*[HW][BFPV]+", "$1")
                .replaceAll("([CGJKQSXZ])[CGJKQSXZ]*[HW][CGJKQSXZ]+", "$1")
                .replaceAll("([DT])[DT]*[HW][DT]+", "$1")
                .replaceAll("L+[HW]L+", "L")
                .replaceAll("([MN])[MN]*[HW][MN]+", "$1")
                .replaceAll("R+[HW]R+", "R");

        rest = rest.replaceAll("([BFPV]){2,}", "$1")
                .replaceAll("(CGJKQSXZ){2,}", "$1")
                .replaceAll("(DT){2,}", "$1")
                .replaceAll("L{2,}", "L")
                .replaceAll("(MN){2,}", "$1")
                .replaceAll("R{2,}", "R");

        rest = rest.replaceAll("[BFPV]", "1")
                .replaceAll("[CGJKQSXZ]", "2")
                .replaceAll("[DT]", "3")
                .replace("L", "4")
                .replaceAll("[MN]", "5")
                .replace("R", "6");

        rest = SoundexUtil.getFirstThreeOrZeros(SoundexUtil.removeDuplicates(rest));
        return first + rest;
    }
}
