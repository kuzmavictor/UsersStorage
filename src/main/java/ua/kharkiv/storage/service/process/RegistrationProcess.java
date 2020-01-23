package ua.kharkiv.storage.service.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.dto.ResponseMessage;
import ua.kharkiv.storage.entity.Locality;
import ua.kharkiv.storage.entity.Region;
import ua.kharkiv.storage.entity.Role;
import ua.kharkiv.storage.entity.User;
import ua.kharkiv.storage.given.Mapper;
import ua.kharkiv.storage.repository.LocalityRepository;
import ua.kharkiv.storage.repository.RegionRepository;
import ua.kharkiv.storage.repository.RoleRepository;
import ua.kharkiv.storage.repository.UserRepository;
import ua.kharkiv.storage.service.RegistrationException;
import ua.kharkiv.storage.validator.Validator;

import java.util.Objects;
import java.util.Optional;

/**
 * The process of registration new users in `UsersStorage` application.
 */
@Service
public class RegistrationProcess implements Process<ResponseMessage, RegistrationCredentials> {

    @Autowired
    @Qualifier("registrationValidator")
    private Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user in UsersStorage application.
     *
     * @param credentials
     *         an instance of {@code RegistrationCredentials}.
     * @throws RegistrationException
     *         if there was a user with the same login(email) registered in the system, or
     *         a user tries to pass invalid login or password values
     */
    @Override
    @Transactional
    public ResponseMessage doHandle(RegistrationCredentials credentials) throws
                                                                         RegistrationException {
        Optional<String> validationErrors = validator.validate(credentials);
        if (validationErrors.isPresent()) {
            throw new RegistrationException(credentials.getEmail(), validationErrors.get());
        }
        String email = credentials.getEmail();
        User newUser = Mapper.userFromRegistrationCredentials(credentials);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        checkIfExist(newUser);
        checkRegion(newUser);
        checkRole(newUser);
        checkLocality(newUser);
        userRepository.save(newUser);

        return new ResponseMessage("The registration was successful.");
    }

    private void checkIfExist(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (Objects.isNull(foundUser)) {
            throw new RegistrationException(user.getEmail(), "The user already exists.");
        }
    }

    private void checkRegion(User user) throws RegistrationException {
        Region userRegion = regionRepository.findByName(user.getRegion()
                                                            .getName());
        if (userRegion == null) {
            throw new RegistrationException(user.getEmail(),
                                            "The target region does not exist.");
        }
    }

    private void checkRole(User user) throws RegistrationException {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        if (roleUser == null) {
            throw new RegistrationException(user.getEmail(),
                                            "The error with the setting of role has occurred. " +
                                                    "Please, contact technical support.");
        }

    }

    //Now, we allow users from the USA does not choose a locality.
    private void checkLocality(User user) throws RegistrationException {
        Locality currentLocality = user.getLocality();

        if (Objects.nonNull(currentLocality)) {
            Locality locality = localityRepository.findByName(currentLocality.getName());
            if (Objects.isNull(locality)) {
                throw new RegistrationException(user.getEmail(),
                                                "The target locality does not exist.");
            }
        }
    }
}
