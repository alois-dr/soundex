package soundex;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Example {
    public static final String FR = "fr";
    public static final String BASIC_FR = "basic_fr";
    public static final String BASIC_ENG = "basic_eng";
    private static final String ENTER_WORD_FR = "Entrez un mot: ";
    private static final String ENTER_WORD_ENG = "Enter a word: ";

    public static void main(String[] args) throws IOException {
        if(args.length < 2) throw new InvalidParameterException("Need dictionary file path and language(fr|basic_fr|basic_eng)");
        Soundex soundex;
        String sentence;
        switch (args[1]){
            case BASIC_ENG:
                soundex = new BasicEngSoundex();
                sentence = ENTER_WORD_ENG;
                break;
            case BASIC_FR:
                sentence = ENTER_WORD_FR;
                soundex = new BasicFrSoundex();
                break;
            case FR:
            default:
                soundex = new FrSoundex();
                sentence = ENTER_WORD_FR;
                break;
        }
        FileDictionary fileDictionary = new FileDictionary(soundex, args[0]);
        Scanner sc = new Scanner(System.in);

        System.out.print(sentence);
        String input = sc.nextLine();
        while (!input.matches("^([qQ](uit|UIT)?|exit|\\\\q)$")) {
            fileDictionary.getAllByWord(input).forEach(System.out::println);
            System.out.println();

            System.out.print(sentence);
            input = sc.nextLine();
        }
    }
}
