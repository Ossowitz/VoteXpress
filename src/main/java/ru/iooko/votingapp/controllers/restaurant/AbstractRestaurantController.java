package ru.iooko.votingapp.controllers.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.dto.RestaurantDTO;
import ru.iooko.votingapp.model.Restaurant;
import ru.iooko.votingapp.service.RestaurantService;
import ru.iooko.votingapp.util.accessory.RestaurantUtil;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.util.List;

@Slf4j
public class AbstractRestaurantController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public List<RestaurantDTO> getAllWithMenu() {
        log.info("getAllRestaurantsWithMenu");
        return RestaurantUtil.getDTOs(service.getAllWithMenu());
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }
}