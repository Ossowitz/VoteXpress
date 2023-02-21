package ru.iooko.votingapp.util.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;
import ru.iooko.votingapp.model.Users;

@Getter @Setter
@ToString(of = "user")
public class AuthUser extends User {

    private final Users user;

    public AuthUser(@NonNull Users user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
