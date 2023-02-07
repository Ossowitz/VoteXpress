package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.exception.NotAllowedUpdateException;
import ru.iooko.votingapp.model.AbstractBaseEntity;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.repository.UserRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.*;
import static ru.iooko.votingapp.util.UserUtil.*;
import static ru.iooko.votingapp.util.validation.ValidationUtil.*;

@Service("userService")
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    // Restrict for modification
    private boolean modificationAllowed = true;

    public Users create(Users user) {
        notNull(user, "user must not be null");
        return save(user);
    }

    public void delete(int id) {
        checkModificationAllowed(id);
        ValidationUtil.getWithCheckNotFoundWithId(repository.delete(id), id);
    }

    public Users get(int id) {
        return getWithCheckNotFoundWithId(repository.get(id), id);
    }

    public Users getByIdWithRoles(int id) {
        return repository.getByIdWithRoles(id);
    }

    public Optional<Users> getByEmail(String email) {
        return repository.getByEmail(email);
    }

    public List<Users> getAll() {
        return repository.getAll();
    }

    public void checkModificationAllowed(int id) {
        // Restrict the modification
        if (modificationAllowed && id <= AbstractBaseEntity.START_SEQ + 10 && id >= AbstractBaseEntity.START_SEQ) {
            throw new NotAllowedUpdateException();
        }
    }

    // prepare user before save to db (including the encode)
    private Users save(Users user) {
        return repository.save(prepareToSave(user));
    }
}