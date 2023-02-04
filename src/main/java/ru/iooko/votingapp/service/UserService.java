package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.iooko.votingapp.exception.NotAllowedUpdateException;
import ru.iooko.votingapp.model.AbstractBaseEntity;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.repository.UserRepository;
import ru.iooko.votingapp.util.UserUtil;
import ru.iooko.votingapp.util.validation.ValidationUtil;

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

    public void delete(int id) {
        checkModificationAllowed(id);
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public void checkModificationAllowed(int id) {
        // Restrict the modification
        if (modificationAllowed && id <= AbstractBaseEntity.START_SEQ + 10 && id >= AbstractBaseEntity.START_SEQ) {
            throw new NotAllowedUpdateException();
        }
    }

    // prepare user before save to db (including the encode)
    private Users save(Users user) {
        return repository.save(UserUtil.prepareToSave(user));
    }
}