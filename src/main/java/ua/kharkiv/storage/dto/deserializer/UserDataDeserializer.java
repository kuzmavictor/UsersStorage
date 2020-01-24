package ua.kharkiv.storage.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import ua.kharkiv.storage.dto.BaseDto;

import java.io.IOException;

public class UserDataDeserializer extends AbstractJsonDeserializer {

    @Override
    public BaseDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return null;
    }
}
