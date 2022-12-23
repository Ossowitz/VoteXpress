package ru.iooko.votingapp.util.accessory;

import lombok.experimental.UtilityClass;
import ru.iooko.votingapp.dto.MenuDTO;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.model.Restaurant;

@UtilityClass
public class MenuUtil {

    public static Menu asTo(MenuDTO menuDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(menuDTO.getId());
        return new Menu(restaurant, DishesUtil.getEntity(menuDTO.getDishes()));
    }
}