package ua.kharkiv.storage.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ua.kharkiv.storage.dto.BaseDto;

import java.io.IOException;

/**
 * An abstract deserializer implementation, which uses to deserialize JSON data to DTOs.
 * <p>Helps map JSON data from requests to immutable objects.
 */
public abstract class AbstractJsonDeserializer extends StdDeserializer<BaseDto> {

    private static final long serialVersionUID = 0L;

    public AbstractJsonDeserializer() {
        this(null);
    }

    AbstractJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes JSON string to data transfer object.
     *
     * @param p
     *         a parser that used for reading JSON content
     * @param ctxt
     *         a context that can be used to access information about this deserialization activity.
     * @return the deserialized value (transfer object)
     */
    @Override
    public abstract BaseDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException;
}
