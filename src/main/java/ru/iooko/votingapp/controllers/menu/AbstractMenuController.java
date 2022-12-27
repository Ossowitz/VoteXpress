package ru.iooko.votingapp.controllers.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.service.MenuService;
import ru.iooko.votingapp.util.validation.ValidationUtil;

@Slf4j
public class AbstractMenuController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MenuService service;

    public Menu getWithDishes(int id) {
        log.info("get menu {}", id);
        return service.getWithDishes(id);
    }

    public Menu create(Menu menu) {
        log.info("create menu {}", menu);
        ValidationUtil.checkNew(menu);
        return service.create(menu);
    }
}