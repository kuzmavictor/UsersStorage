package ua.kharkiv.storage.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.dto.ResponseMessage;
import ua.kharkiv.storage.dto.serializer.CustomJsonSerializer;
import ua.kharkiv.storage.manager.DataSourceManager;
import ua.kharkiv.storage.service.UserServiceFacade;

/**
 * A REST endpoint witch responsible for registration new users in UsersStorage application.
 */
@RestController
@RequestMapping("api/v1/storage")
public class RegistrationController {

    @Autowired
    CustomJsonSerializer customJsonSerializer;

    @Autowired
    DataSourceManager dataSourceManager;

    @Autowired
    UserServiceFacade userServiceFacade;

    @PostMapping("/register")

    @ApiOperation(value = "Register a new user in `UsersStorage` application.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The operation of registering new users " +
                    "was successful."),
            @ApiResponse(code = 400, message = "Bad request, adjust before retrying."),
            @ApiResponse(code = 409, message = "The user with the same login already exists.")})
    public ResponseEntity<?> registerUser(@ApiParam("New User")
                                          @RequestBody RegistrationCredentials credentials) {
        System.out.println("cred=====> " + credentials);
        dataSourceManager.setDataSourceForRegistration(credentials);
        ResponseMessage response = userServiceFacade.register(credentials);
        String json = customJsonSerializer.serializeResponseMessage(response);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(json);
    }

}
