package ua.kharkiv.storage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.given.ErrorHandler;

import java.util.Objects;
import java.util.Optional;

import static ua.kharkiv.storage.given.Patterns.PATTERN_FOR_EMAIL;
import static ua.kharkiv.storage.given.Patterns.PATTERN_FOR_NAME;
import static ua.kharkiv.storage.given.Patterns.PATTERN_FOR_PASSWORD;
import static ua.kharkiv.storage.given.Patterns.PATTERN_FOR_USER_CREDENTIALS;

@Service
public class RegistrationValidator extends Validator<RegistrationCredentials> {

    @Autowired
    ErrorHandler errorHandler;

    @Override
    public Optional<String> validate(RegistrationCredentials credentials) {

        boolean isMatchFirstName = isMatch(PATTERN_FOR_USER_CREDENTIALS,
                                           credentials.getFirstName());
        if (!isMatchFirstName) {
            String errorMessage =
                    "The first name can contain only letters, numbers, hyphens, spaces.";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isMatchLastName = isMatch(PATTERN_FOR_USER_CREDENTIALS, credentials.getLastName());
        if (!isMatchLastName) {
            String errorMessage =
                    "The last name can contain only letters, numbers, hyphens, spaces.";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isMatchEmail = isMatch(PATTERN_FOR_EMAIL, credentials.getLastName());
        if (!isMatchEmail) {
            String errorMessage =
                    "The email must be valid.";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isMatchPassword = isMatch(PATTERN_FOR_PASSWORD, credentials.getPassword());
        if (!isMatchPassword) {
            String errorMessage =
                    "Password must contain minimum six characters, at least one uppercase " +
                            "letter, one lowercase letter and one number.";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isMatchCountry = isMatch(PATTERN_FOR_NAME, credentials.getCountry());
        if (!isMatchEmail) {
            String errorMessage =
                    "The country name mast be valid";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isMatchRegion = isMatch(PATTERN_FOR_NAME, credentials.getRegion());
        if (!isMatchEmail) {
            String errorMessage =
                    "The region name  must be valid.";
            errorHandler.updateErrorMessage(errorMessage);
        }

        boolean isCanada = Objects.equals(credentials.getCountry(), "Canada");
        boolean notEmpty = Objects.isNull(credentials.getLocality());
        if (isCanada) {
            validateLocality(credentials.getLocality());

        } else if (notEmpty) {
            validateLocality(credentials.getLocality());
        }

        return errorHandler.errors();
    }

    private void validateLocality(String locality) {
        boolean isMatchLocality = isMatch(PATTERN_FOR_NAME, locality);
        if (!isMatchLocality) {

            String errorMessage =
                    "The locality must be valid.";
            errorHandler.updateErrorMessage(errorMessage);
        }
    }
}
