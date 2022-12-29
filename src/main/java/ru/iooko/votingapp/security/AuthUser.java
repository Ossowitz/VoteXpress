package ru.iooko.votingapp.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;
import ru.iooko.votingapp.model.Users;

@Getter @Setter
@ToString(of = "user")
public class AuthUser extends User {

    /**
     * represents information about a user
     *      who has been successfully authenticated into the system
     */
    private final Users user;

    public AuthUser(@NonNull Users user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
