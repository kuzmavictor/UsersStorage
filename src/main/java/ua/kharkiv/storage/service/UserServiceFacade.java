package ua.kharkiv.storage.service;

import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.dto.ResponseMessage;

/**
 * A service provides handling operations with data of users.
 */
public interface UserServiceFacade {
    ResponseMessage register(RegistrationCredentials credentials);

}
