package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.repository.UserRepository;
import ru.iooko.votingapp.util.UserUtil;

@Service("userService")
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    // Restrict for modification
    private boolean modificationAllowed = true;

    public Users create(Users user) {
        Assert.notNull(user, "user must not be null");
        return save(user);
    }

    // prepare user before save to db (including the encode)
    private Users save(Users user) {
        return repository.save(UserUtil.prepareToSave(user));
    }
}