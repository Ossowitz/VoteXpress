package ru.iooko.votingapp.util.accessory;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.model.Users;

@UtilityClass
public class UserUtil {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.
            createDelegatingPasswordEncoder();

    /**
     * @brief https://habr.com/ru/companies/otus/articles/552412/
     * @// TODO: implement pattern builder
     */
    public static UsersDTO asTo(Users users) {
        return new UsersDTO(users.getId(), users.getName(), users.getEmail(), users.getPassword());
    }

    public static Users updateFromTo(Users users, UsersDTO usersDto) {
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail().toLowerCase());
        users.setPassword(usersDto.getPassword());
        return users;
    }

    public static Users prepareToSave(Users users) {
        users.getRoles().forEach(roles -> roles.setUser(users));
        users.setPassword(PASSWORD_ENCODER.encode(users.getPassword()));
        users.setEmail(users.getEmail().toLowerCase());
        return users;
    }
}