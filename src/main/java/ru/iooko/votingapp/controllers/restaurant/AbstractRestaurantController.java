package ru.iooko.votingapp.controllers.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.service.RestaurantService;

@Slf4j
public class AbstractRestaurantController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RestaurantService service;
}