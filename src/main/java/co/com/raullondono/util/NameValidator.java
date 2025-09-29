package co.com.raullondono.util;

import co.com.raullondono.exceptions.NameValidationException;

public final class NameValidator {

    private static final String ONLY_LETTERS = "^[\\p{L}]+$";

    private NameValidator() {
    }

    public static void validateAndFormatSingleWord(String raw, String fieldLabel) {
        if (raw == null) {
            throw new NameValidationException(fieldLabel + " can't be null.");
        }
        String s = raw.trim();
        if (s.isEmpty()) {
            throw new NameValidationException(fieldLabel + " can't be empty.");
        }
        if (!s.matches(ONLY_LETTERS)) {
            throw new NameValidationException(fieldLabel
                    + " must contain a single word and only letters (no spaces or symbols). Value:'" + raw + "'");
        }
    }
}
