package ua.kharkiv.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.Immutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An object that contains response message.
 */
@Immutable
@Getter
public class ResponseMessage implements BaseDto {

    @ApiModelProperty(position = 0)
    @JsonProperty("message")
    private final String responseMessage;

    /**
     * Creates an instance of {@code ResponseMessage}
     *
     * @param responseMessage
     *         a response message that informs client-side about
     *         the status of the performed operation
     */
    public ResponseMessage(String responseMessage) {
        this.responseMessage = checkNotNull(responseMessage);
    }

}
