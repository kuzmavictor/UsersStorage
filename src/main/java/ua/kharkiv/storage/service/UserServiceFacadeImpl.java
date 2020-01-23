package ua.kharkiv.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.dto.ResponseMessage;
import ua.kharkiv.storage.service.process.Process;
import ua.kharkiv.storage.service.process.RegistrationProcess;

/**
 * A simple facade to wrap all business logic in this application.
 * <p> The full implementation of all service layers contains in processes.
 *
 * @see Process
 * @see RegistrationProcess
 */

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
    @Autowired
    Process registrationProcess;


    @Override
    public ResponseMessage register(RegistrationCredentials credentials) {
        return (ResponseMessage) registrationProcess.doHandle(credentials);
    }
}

