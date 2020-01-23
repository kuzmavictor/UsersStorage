package ua.kharkiv.storage.dto.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kharkiv.storage.dto.ResponseMessage;

/**
 * The wrapper for serialization DTOs to JSON.
 * <p>Contains a set of methods that allow you to serialize objects to a string.
 */
@Slf4j
@Service
public class CustomJsonSerializer {

    @Autowired
    ObjectMapper objectMapper;

    public String serializeResponseMessage
            (ResponseMessage responseMessage) throws
                                              JsonSerializationException {
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(responseMessage);
        } catch (JsonProcessingException e) {
            if (log.isErrorEnabled()) {
                log.error("Error of serialization a response message to JSON");
                throw new JsonSerializationException("Cannot represent data from response message");
            }

        }
        return jsonString;
    }

}
