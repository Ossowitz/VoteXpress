package ru.iooko.votingapp.web.controllers.users;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.iooko.votingapp.model.Users;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = ProfileController.REST_URL,
        produces = APPLICATION_JSON_VALUE)
public class AdminController extends AbstractUserController {

    protected static final String REST_URL = "/api/users";

    @GetMapping
    public List<Users> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public Users getByIdWithRoles(@PathVariable int id) {
        return super.getByIdWithRoles(id);
    }

    @GetMapping("/by")
    public Users getByMail(@RequestParam String email) {
        return super.getByMail(email);
    }
}