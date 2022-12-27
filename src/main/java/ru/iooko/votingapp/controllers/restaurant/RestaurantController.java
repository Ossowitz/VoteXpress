package ru.iooko.votingapp.controllers.restaurant;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = RestaurantController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractRestaurantController {

    protected static final String REST_URL = "/api/restaurants";
}