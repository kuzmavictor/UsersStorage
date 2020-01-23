package ua.kharkiv.storage.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.given.ErrorHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static ua.kharkiv.storage.given.JsonKeys.COUNTRY;
import static ua.kharkiv.storage.given.JsonKeys.EMAIL;
import static ua.kharkiv.storage.given.JsonKeys.FIRST_NAME;
import static ua.kharkiv.storage.given.JsonKeys.LAST_NAME;
import static ua.kharkiv.storage.given.JsonKeys.LOCALITY;
import static ua.kharkiv.storage.given.JsonKeys.PASSWORD;
import static ua.kharkiv.storage.given.JsonKeys.REGION;

/**
 * The custom implementation of JSON deserializer for {@link RegistrationCredentials} class.
 */
@SuppressWarnings("ConstantConditions") // the {@code JsonNode} element cannot be null
public class RegistrationCredentialsDeserializer extends AbstractJsonDeserializer {

    private static final long serialVersionUID = 0L;

    /**
     * {@inheritDoc}
     *
     * <p>Allows to override the deserialization behavior of the request body {@link ResponseBody}
     * for the immutable {@code RegistrationCredentials} object.
     */
    @Override
    public RegistrationCredentials deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonDeserializerException {
        ErrorHandler errorHandler = new ErrorHandler();

        JsonNode rootNode = p.getCodec()
                             .readTree(p);

        JsonNode firstName = rootNode.get(FIRST_NAME);
        JsonNode lastName = rootNode.get(LAST_NAME);
        JsonNode email = rootNode.get(EMAIL);
        JsonNode password = rootNode.get(PASSWORD);
        JsonNode country = rootNode.get(COUNTRY);
        JsonNode region = rootNode.get(REGION);
        JsonNode locality = rootNode.get(LOCALITY);

        if (firstName == null) {
            String nameErrorMessage = "Cannot obtain the data about the first name.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (lastName == null) {
            String descriptionErrorMessage = "Cannot obtain the data about the last name";
            errorHandler.updateErrorMessage(descriptionErrorMessage);
        }

        if (email == null) {
            String nameErrorMessage = "Cannot obtain the data about the email.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (password == null) {
            String nameErrorMessage = "Cannot obtain the data about the password.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (country == null) {
            String nameErrorMessage = "Cannot obtain the data about the country.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (region == null) {
            String nameErrorMessage = "Cannot obtain the data about the region.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (locality == null) {
            if (Objects.equals(country.asText(), "Canada")) {
                String nameErrorMessage = "Cannot obtain the data about the locality.";
                errorHandler.updateErrorMessage(nameErrorMessage);
            }
        }

        Optional<String> errors = errorHandler.errors();
        if (errors.isPresent()) {
            throw new JsonDeserializerException(errors.get());
        }

        String firstNameUser = firstName.asText();
        String lastNameUser = lastName.asText();
        String emailUser = email.asText();
        String passwordUser = password.asText();
        String countryUser = country.asText();
        String regionUser = region.asText();
        String localityUser = locality.asText();

        return new RegistrationCredentials(firstNameUser, lastNameUser, emailUser, passwordUser,
                                           countryUser, regionUser, localityUser);

    }
}
