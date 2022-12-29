package ru.iooko.votingapp.web.controllers.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.service.DishService;

@Slf4j
public class AbstractDishController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DishService service;
}