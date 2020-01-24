package ua.kharkiv.storage.security;

import org.springframework.http.HttpStatus;

/**
 * This exception is thrown if process of handling JWT token was failed.
 */
public class JwtAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    /**
     * Initializes an exception.
     *
     * @param message
     *         additional details to describe the problem properly
     * @param httpStatus
     *         a code of error {@link HttpStatus}
     */
    public JwtAuthenticationException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Obtains the code of {@code HttpStatus}.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
