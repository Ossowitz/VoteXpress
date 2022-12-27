package ru.iooko.votingapp.controllers.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.service.MenuService;

@Slf4j
public class AbstractMenuController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MenuService service;
}