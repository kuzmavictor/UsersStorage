package ua.kharkiv.storage.given;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ua.kharkiv.storage.dto.deserializer.RegistrationCredentialsDeserializer;
import ua.kharkiv.storage.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple handler of error that occur during deserialization JSON string to objects
 * or validation some data in `UserStorage` application.
 *
 * @see RegistrationCredentialsDeserializer
 * @see Validator
 */
@Service
public class ErrorHandler {

    private static final String MESSAGE_KEY = "message";

    @Getter
    @JsonSerialize(keyUsing = MapSerializer.class)
    private final Map<String, String> requestErrors;

    /**
     * Creates an instance of {@code ErrorHandler} and initializes the {@linkplain  Map map}
     * that serves as container to some deserialization or validation errors.
     */
    public ErrorHandler() {
        this.requestErrors = new HashMap<>();
    }

    /**
     * Adds a new error message or update {@link #requestErrors} if the error message exists.
     *
     * @param errorMessage
     *         an error message
     */
    public void updateErrorMessage(String errorMessage) {
        checkNotNull(errorMessage);

        boolean hasError = requestErrors.containsKey(MESSAGE_KEY);
        if (hasError) {
            String oldErrorMessage = requestErrors.get(MESSAGE_KEY);
            String updatedErrorMessage = oldErrorMessage + ' ' + errorMessage;
            requestErrors.put(MESSAGE_KEY, updatedErrorMessage);
        } else {
            requestErrors.put(MESSAGE_KEY, errorMessage);
        }
    }

    /**
     * Returns the {@code String} that contains errors that occur during
     * deserialization JSON string from requests or validation process.
     *
     * @return the string with groups error messages or
     *         {@code Optional.empty()} if errors do not exist
     */
    public Optional<String> errors() {
        if (requestErrors.isEmpty()) {
            return Optional.empty();
        }
        String errorMessages = requestErrors.get(MESSAGE_KEY);

        return Optional.of(errorMessages);
    }
}
