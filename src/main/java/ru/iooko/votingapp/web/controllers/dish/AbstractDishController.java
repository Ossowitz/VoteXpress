package ru.iooko.votingapp.web.controllers.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.model.Dish;
import ru.iooko.votingapp.service.DishService;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.util.List;

@Slf4j
public class AbstractDishController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DishService service;

    public Dish get(int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }

    public List<Dish> getAll() {
        log.info("getAll dish");
        return service.getAll();
    }

    public Dish create(Dish dish) {
        log.info("create dish {}", dish);
        ValidationUtil.checkNew(dish);
        return service.create(dish);
    }

    public void update(Dish dish, int id) {
        log.info("update dish {} with id {}", dish, id);
        ValidationUtil.assureIdConsistent(dish, id);
        service.update(dish);
    }

    public void delete(int id) {
        log.info("delete dish {}", id);
        service.delete(id);
    }
}