package ru.iooko.votingapp.web.controllers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.util.security.SecurityUtil;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static ru.iooko.votingapp.util.security.SecurityUtil.*;

@RestController
@Secured("ROLE_USER")
@RequestMapping(value = ProfileController.REST_URL,
        produces = APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {

    protected static final String REST_URL = "api/profile";

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(NO_CONTENT)
    public void update(@Valid @RequestBody UsersDTO userDTO) {
        super.update(userDTO, authUserId());
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void delete() {
        super.delete(authUserId());
    }

    @GetMapping
    public Users getByIdWithRoles(int id) {
        return super.getByIdWithRoles(authUserId());
    }
}