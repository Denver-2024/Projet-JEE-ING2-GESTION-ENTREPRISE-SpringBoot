package fr.cytech.projetjeespring.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    public CustomUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities,
                             String firstName, String lastName) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}