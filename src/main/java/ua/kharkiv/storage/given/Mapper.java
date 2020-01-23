package ua.kharkiv.storage.given;

import ua.kharkiv.storage.dto.RegistrationCredentials;
import ua.kharkiv.storage.entity.Locality;
import ua.kharkiv.storage.entity.Region;
import ua.kharkiv.storage.entity.Role;
import ua.kharkiv.storage.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple mapper implementation using native java features.
 * <p>Allows mapping data from immutable DTOs to entities and vise versa.
 */
public class Mapper {

    /**
     * Creates entity {@code User} using data from {@code RegistrationCredentials}.
     *
     * @param credentials
     *         an instance of {@code RegistrationCredentials}
     * @return the filling entity {@code User}
     */
    public static User userFromRegistrationCredentials(RegistrationCredentials credentials) {
        Region region = new Region();
        region.setName(credentials.getRegion());

        Locality locality = new Locality();
        locality.setName(credentials.getLocality());

        Role userRole = new Role();
        userRole.setName("USER_ROLE");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);

        User user = new User();
        user.setFirstName(credentials.getFirstName());
        user.setLastName(credentials.getLastName());
        user.setEmail(credentials.getEmail());
        user.setPassword(credentials.getPassword());
        user.setRoles(roles);
        user.setRegion(region);
        user.setLocality(locality);
        user.setDeleted(false);

        return user;
    }
}
