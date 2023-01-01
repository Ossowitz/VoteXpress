package ru.iooko.votingapp.web.controllers.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.service.UserService;

import java.util.List;

import static ru.iooko.votingapp.util.validation.ValidationUtil.*;

@Slf4j
public class AbstractUserController {

    @Autowired
    private UserService service;

    public Users get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<Users> getAll() {
        log.info("getAll users");
        return service.getAll();
    }

    public Users getByIdWithRoles(int id) {
        log.info("getByIdWithRoles {}", id);
        return service.getByIdWithRoles(id);
    }

    public Users create(Users user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void update(Users user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UsersDTO userDTO, int id) {
        log.info("update {} with id={}", userDTO, id);
        assureIdConsistent(userDTO, id);
        service.update(userDTO);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Users getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enableUser(id, enabled);
    }
}