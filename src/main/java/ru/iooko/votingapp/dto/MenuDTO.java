package ru.iooko.votingapp.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuDTO extends AbstractBaseDTO {

    @NotNull
    List<DishesDTO> dishes;

    @ConstructorProperties({"restaurantId", "dishes"})
    public MenuDTO(Integer restaurantId, List<DishesDTO> dishes) {
        super(restaurantId);
        this.dishes = dishes;
    }
}