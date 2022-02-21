package soundex;

import jakarta.validation.constraints.NotNull;

public class EmptyWordException extends Exception {
    public EmptyWordException() {
    }

    public EmptyWordException(@NotNull String s) {
        super(s);
    }
}
