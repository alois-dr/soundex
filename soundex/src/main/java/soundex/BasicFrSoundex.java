package soundex;

public class BasicFrSoundex implements Soundex {
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
        rest = rest.replaceAll("([BP])[BP]*[HW][BP]+", "$1")
                .replaceAll("([CKQ])[CKQ]*[HW][CKQ]+", "$1")
                .replaceAll("([DT])[DT]*[HW][DT]+", "$1")
                .replaceAll("L+[HW]L+", "L")
                .replaceAll("([MN])[MN]*[HW][MN]+", "$1")
                .replaceAll("R+[HW]R+", "R")
                .replaceAll("([GJ])[GJ]*[HW][GJ]+", "$1")
                .replaceAll("([XZS])[XZS]*[HW][XZS]+", "$1")
                .replaceAll("([FV])[FV]*[HW][FV]+", "$1");

        rest = rest.replaceAll("([BP])[BP]+", "$1")
                .replaceAll("([CKQ])[CKQ]+", "$1")
                .replaceAll("([DT])[DT]+", "$1")
                .replaceAll("L{2,}", "L")
                .replaceAll("([MN])[MN]+", "$1")
                .replaceAll("R{2,}", "R")
                .replaceAll("([GJ])[GJ]+", "$1")
                .replaceAll("([XZS])[XZS]+", "$1")
                .replaceAll("([FV])[FV]+", "$1");

        rest = rest.replaceAll("[BP]", "1")
                .replaceAll("[CKQ]", "2")
                .replaceAll("[DT]", "3")
                .replace("L", "4")
                .replaceAll("[MN]", "5")
                .replace("R", "6")
                .replaceAll("[GJ]", "7")
                .replaceAll("[XZS]", "8")
                .replaceAll("[FV]", "9");

        rest = SoundexUtil.getFirstThreeOrZeros(SoundexUtil.removeDuplicates(rest));

        return first + rest;
    }
}
