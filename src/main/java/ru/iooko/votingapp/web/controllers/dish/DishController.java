package ru.iooko.votingapp.web.controllers.dish;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iooko.votingapp.model.Dish;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = DishController.REST_URL,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController extends AbstractDishController {

    protected static final String REST_URL = "/api/dishes";

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        return super.getAll();
    }

}