package soundex;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public interface Soundex {
    @NotEmpty String generate(@NotNull final String word) throws EmptyWordException;
}
