package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.dto.UsersDTO;
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
    private boolean isModificationAllowed = true;

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

    @Transactional
    public void enableUser(int id, boolean enabled) {
        checkModificationAllowed(id);
        Users user = get(id);
        user.setEnabled(enabled);
    }

    @Transactional
    public void update(Users user) {
        notNull(user, "user must not be null");
        checkModificationAllowed(user.getId());
        save(user);
    }

    @Transactional
    public void update(UsersDTO userDTO) {
        checkModificationAllowed(userDTO.getId());
        Users user = get(userDTO.getId());
        save(updateFromTo(user, userDTO));
    }

    // prepare user before save to db (including the encode)
    private Users save(Users user) {
        return repository.save(prepareToSave(user));
    }

    private void checkModificationAllowed(int id) {
        // Restrict the modification
        boolean isRestrictInterval = id <= AbstractBaseEntity.START_SEQ + 10 && id >= AbstractBaseEntity.START_SEQ;
        if (isModificationAllowed && isRestrictInterval) {
            throw new NotAllowedUpdateException();
        }
    }
}