package ua.kharkiv.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.Immutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import ua.kharkiv.storage.dto.deserializer.RegistrationCredentialsDeserializer;

/**
 * An object that contains data about new user that try to register.
 *
 * The {@link #locality} for users from USA may be undefined.
 */
@Immutable
@Getter
@RequiredArgsConstructor
@JsonDeserialize(using = RegistrationCredentialsDeserializer.class)
public class RegistrationCredentials implements BaseDto {

    @ApiModelProperty(position = 0)
    @JsonProperty("firstName")
    private final String firstName;

    @ApiModelProperty(position = 1)
    @JsonProperty("lastName")
    private final String lastName;

    @ApiModelProperty(position = 2)
    @JsonProperty("email")
    private final String email;

    @ApiModelProperty(position = 3)
    @JsonProperty("password")
    private final String password;

    @ApiModelProperty(position = 4)
    @JsonProperty("country")
    private final String country;

    @ApiModelProperty(position = 5)
    @JsonProperty("region")
    private final String region;

    @ApiModelProperty(position = 6)
    @JsonProperty("locality")
    @Nullable
    private final String locality;
}
