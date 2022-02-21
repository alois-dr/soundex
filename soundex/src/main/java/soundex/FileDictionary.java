package soundex;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.*;

public class FileDictionary implements Repository {
    private final Map<String, Set<String>> soundexWords;
    private final Soundex soundex;
    public FileDictionary(Soundex soundex, @NotNull String filePath) throws IOException {
        this.soundex = soundex;
        soundexWords = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.ISO_8859_1))){
            reader.lines().forEach(w -> {
                try {
                    save(w, this.soundex.generate(w));
                } catch (EmptyWordException e) {
                    //System.out.println(e.getMessage());
                }
            });
        }
    }

    @Override
    public void save(@NotEmpty String word, @NotEmpty String code) {
        if(word == null || code == null) throw new InvalidParameterException();
        Set<String> words = this.soundexWords.getOrDefault(code, new HashSet<>());
        words.add(word);
        this.soundexWords.put(code, words);
    }

    @Override
    public List<String> getAllBySoundex(String code) {
        return new ArrayList<>(soundexWords.getOrDefault(code, Collections.emptySet()));
    }

    @Override
    public List<String> getAllByWord(String word) {
        try {
            return getAllBySoundex(this.soundex.generate(word));
        } catch (EmptyWordException e) {
            //
        }
        return Collections.emptyList();
    }

}
