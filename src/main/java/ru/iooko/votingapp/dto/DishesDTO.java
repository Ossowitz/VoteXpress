package ru.iooko.votingapp.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishesDTO extends AbstractBaseDTO {

    @NotBlank
    @Range(max = 50000)
    Integer price;

    @ConstructorProperties({"dishId", "price"})
    public DishesDTO(Integer dishId, Integer price) {
        super(dishId);
        this.price = price;
    }
}