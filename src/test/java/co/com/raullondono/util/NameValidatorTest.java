package co.com.raullondono.util;

import co.com.raullondono.exceptions.NameValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"Raul", "José", "Álvaro", "Ana"})
    void validate_shouldCapitalizeAndAcceptSingleWord(String input) {
        assertDoesNotThrow(() -> NameValidator.validateAndFormatSingleWord(input, "name"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Raul Andres", "Ana-Maria", "O'Connor", "123", ""})
    void validate_shouldRejectCompositeOrInvalid(String input) {
        assertThatThrownBy(() -> NameValidator.validateAndFormatSingleWord(input, "Nombre"))
                .isInstanceOf(NameValidationException.class);
    }
}
