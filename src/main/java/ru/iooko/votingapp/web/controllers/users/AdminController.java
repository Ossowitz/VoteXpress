package ru.iooko.votingapp.web.controllers.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.iooko.votingapp.model.Users;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = AdminController.REST_URL,
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

    /**
     * allows you to create new users in a Spring application
     *      and return information about the created user along with a URI to access it
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user)  {
        Users createdUser = super.create(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Users user, @PathVariable int id) {
        super.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}