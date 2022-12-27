package ru.iooko.votingapp.controllers.restaurant;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iooko.votingapp.dto.RestaurantDTO;
import ru.iooko.votingapp.model.Restaurant;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = RestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractRestaurantController {

    protected static final String REST_URL = "/api/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<RestaurantDTO> getAllWithMenu() {
        return super.getAllWithMenu();
    }
}