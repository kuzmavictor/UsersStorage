package ua.kharkiv.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.kharkiv.storage.dto.ResponseMessage;
import ua.kharkiv.storage.dto.deserializer.JsonDeserializerException;
import ua.kharkiv.storage.dto.serializer.CustomJsonSerializer;
import ua.kharkiv.storage.dto.serializer.JsonSerializationException;
import ua.kharkiv.storage.manager.DataSourceManagerException;

/**
 * A specified endpoint that centralized handlers exception in this application.
 * <p>Allows providing responses about errors with necessary HTTP codes for client-side in more
 * readability format.
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Autowired
    CustomJsonSerializer customJsonSerializer;

    /**
     * Returns to client the message and code error
     * when {@code JsonSerializationException} is occurred.
     *
     * @param exception
     *         an instance of {@code JsonSerializationException}
     * @return the {@code ResponseEntity} with given HTTP code and body;
     */
    @ExceptionHandler(value = JsonSerializationException.class)
    public ResponseEntity<?> handleJsonSerializationException(
            JsonSerializationException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = customJsonSerializer.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Returns to client the message and code error
     * when {@code JsonDeserializerException} is occurred.
     *
     * @param exception
     *         an object of {@code JsonDeserializerException}
     * @return the {@code ResponseEntity} with given HTTP code and body
     */
    @ExceptionHandler(value = JsonDeserializerException.class)
    public ResponseEntity<?> handleCreateDtoException(JsonDeserializerException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = customJsonSerializer.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns to client the message and code error
     * when {@code DataSourceManagerException} is occurred.
     *
     * @param exception
     *         an object of {@code DataSourceManagerException}
     * @return the {@code ResponseEntity} with given HTTP code and body
     */
    @ExceptionHandler(value = DataSourceManagerException.class)
    public ResponseEntity<?> handleCatalogOperationException(DataSourceManagerException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = customJsonSerializer.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

}
