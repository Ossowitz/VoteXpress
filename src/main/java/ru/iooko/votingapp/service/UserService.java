package ru.iooko.votingapp.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.exception.NotAllowedUpdateException;
import ru.iooko.votingapp.model.AbstractBaseEntity;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.repository.UserRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.util.List;

import static org.springframework.util.Assert.notNull;
import static ru.iooko.votingapp.util.UserUtil.prepareToSave;
import static ru.iooko.votingapp.util.UserUtil.updateFromTo;
import static ru.iooko.votingapp.util.validation.ValidationUtil.checkNotFound;
import static ru.iooko.votingapp.util.validation.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    private final UserRepository repository;

    // Restrict for modification
    private boolean isModificationAllowed = true;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Users create(Users user) {
        notNull(user, "user must not be null");
        return save(user);
    }

    public void delete(int id) {
        checkModificationAllowed(id);
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public Users get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Users getByIdWithRoles(int id) {
        return repository.getByIdWithRoles(id);
    }

    public Users getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email).orElse(null), "email=" + email);
    }

    public List<Users> getAll() {
        return repository.getAll();
    }

    public void update(Users user) {
        notNull(user, "user must not be null");
        checkModificationAllowed(user.id());
        save(user);
    }

    public void update(UsersDTO userDTO) {
        checkModificationAllowed(userDTO.id());
        Users user = get(userDTO.id());
        save(updateFromTo(user, userDTO));
    }

    public void enableUser(int id, boolean enabled) {
        checkModificationAllowed(id);
        Users user = get(id);
        user.setEnabled(enabled);
    }

    // prepare user before save to db (including the encode)
    private Users save(Users user) {
        return repository.save(prepareToSave(user));
    }

    public void switchOfModificationRestriction() {
        this.isModificationAllowed = false;
    }

    public void switchOnModificationRestriction() {
        this.isModificationAllowed = true;
    }

    protected void checkModificationAllowed(int id) {
        // Restrict the modification
        boolean isRestrictInterval = id <= AbstractBaseEntity.START_SEQ + 10 && id >= AbstractBaseEntity.START_SEQ;
        if (isModificationAllowed && isRestrictInterval) {
            throw new NotAllowedUpdateException();
        }
    }
}