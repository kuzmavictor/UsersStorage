package ua.kharkiv.storage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.kharkiv.storage.entity.Role;
import ua.kharkiv.storage.entity.User;
import ua.kharkiv.storage.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthenticatedUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User foundedUser = userRepository.findByEmail(username);

        if (Objects.isNull(foundedUser)) {
            throw new UsernameNotFoundException("User with the login '"
                                                        + username + "' not found.");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(foundedUser.getPassword())
                .authorities(mapToGrantedAuthorities(new ArrayList<>(foundedUser.getRoles())))
                .disabled(foundedUser.isDeleted())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                        .map(role ->
                                     new SimpleGrantedAuthority(role.getName())
                        )
                        .collect(Collectors.toList());
    }
}
