package soundex;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface Repository {
    void save(String word, String code);
    @NotNull List<String> getAllBySoundex(String code);
    @NotNull List<String> getAllByWord(String word);
}
