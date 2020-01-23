package ua.kharkiv.storage.validator;

import ua.kharkiv.storage.dto.BaseDto;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The base validator of `UsersStorage` application.
 */
public abstract class Validator<T extends BaseDto> {

    public abstract Optional<String> validate(T t);

    protected static boolean isMatch(String regex, String data) {
        return Pattern.compile(regex)
                      .matcher(data)
                      .find();
    }

}
