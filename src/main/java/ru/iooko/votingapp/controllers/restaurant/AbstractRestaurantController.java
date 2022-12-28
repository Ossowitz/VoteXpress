package ru.iooko.votingapp.controllers.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.dto.RestaurantDTO;
import ru.iooko.votingapp.model.Restaurant;
import ru.iooko.votingapp.service.RestaurantService;
import ru.iooko.votingapp.util.accessory.RestaurantUtil;

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
}