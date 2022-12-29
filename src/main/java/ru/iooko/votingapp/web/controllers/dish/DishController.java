package ru.iooko.votingapp.web.controllers.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.iooko.votingapp.model.Dish;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDish(@Valid @RequestBody Dish dish) {
        Dish createdDish = super.create(dish);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdDish.getId()).toUri();
        return ResponseEntity.created(uri).body(createdDish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

}