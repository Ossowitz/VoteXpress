package ru.iooko.votingapp.web.controllers.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.service.UserService;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class AbstractUserController {

    private final UserService service;

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
        ValidationUtil.checkNew(user);
        return service.create(user);
    }

    public void update(Users user, int id) {
        log.info("update {} with id={}", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UsersDTO userDTO, int id) {
        log.info("update {} with id={}", userDTO, id);
        ValidationUtil.assureIdConsistent(userDTO, id);
        service.update(userDTO);
    }
}