package ua.kharkiv.storage.given;

/**
 * Provides the keys of JSON data that handles in this application.
 */
public abstract class JsonKeys {

    /**
     * Prevents instantiation of {@code JsonKey}.
     */
    private JsonKeys() {

    }

    public static final String IDENTIFIER = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String COUNTRY = "country";
    public static final String REGION = "region";
    public static final String LOCALITY = "locality";
}
