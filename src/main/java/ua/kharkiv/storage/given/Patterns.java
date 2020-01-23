package ua.kharkiv.storage.given;

/**
 * Provides the regex patterns.
 */
public abstract  class Patterns {

    /**
     * Prevents instantiation of {@code Patterns}.
     */
    private Patterns() {
    }

    public  static final String PATTERN_FOR_EMAIL =
            "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final String PATTERN_FOR_PASSWORD
            = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,224}$";
    public static final String PATTERN_FOR_USER_CREDENTIALS =
            "(?i)(^[a-z0-9])((?![ .,'-]$)[a-z0-9 .,'-]){1,224}$";

    public static final String PATTERN_FOR_NAME = "^[a-zA-Z0-9]+(?:[\\s-][a-zA-Z0-9]+)*$";


}
