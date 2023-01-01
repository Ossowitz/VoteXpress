package ru.iooko.votingapp.web.controllers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.iooko.votingapp.dto.UsersDTO;
import ru.iooko.votingapp.util.security.SecurityUtil;

import javax.validation.Valid;

@RestController
@Secured("ROLE_USER")
@RequestMapping(value = ProfileController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {

    protected static final String REST_URL = "api/profile";

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UsersDTO userDTO) {
        super.update(userDTO, SecurityUtil.authUserId());
    }
}