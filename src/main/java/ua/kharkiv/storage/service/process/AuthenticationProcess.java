package ua.kharkiv.storage.service.process;

import ua.kharkiv.storage.dto.AuthorizationCredentials;
import ua.kharkiv.storage.dto.ResponseMessage;

public class AuthenticationProcess implements
                                   Process<ResponseMessage, AuthorizationCredentials> {

    @Override
    public ResponseMessage doHandle(AuthorizationCredentials parameter) {
        return null;
    }
}
