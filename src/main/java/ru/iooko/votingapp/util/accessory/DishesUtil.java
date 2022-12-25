package ru.iooko.votingapp.util.accessory;

import lombok.experimental.UtilityClass;
import ru.iooko.votingapp.dto.DishesDTO;
import ru.iooko.votingapp.model.Dish;
import ru.iooko.votingapp.model.Dishes;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class DishesUtil {

    public static Dishes asTo(DishesDTO dishesDTO) {
        Dish dish = new Dish();
        dish.setId(dishesDTO.getId());
        return new Dishes(dish, dishesDTO.getPrice());
    }

    public static Set<Dishes> getEntity(List<DishesDTO> dishesDTOList) {
        return dishesDTOList.stream()
                .map(DishesUtil::asTo)
                .collect(toSet());
    }
}