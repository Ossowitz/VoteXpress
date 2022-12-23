package ru.iooko.votingapp.controllers.menu;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iooko.votingapp.model.Menu;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController extends AbstractMenuController {

    protected static final String REST_URL = "/api/menues";

    @GetMapping("/{id}")
    public Menu getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }
}