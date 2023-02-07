package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.iooko.votingapp.model.Dish;
import ru.iooko.votingapp.repository.DishRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

@Service("dishService")
@AllArgsConstructor
public class DishService {

    private final DishRepository repository;

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return ValidationUtil.checkNotFoundWithId(repository.save(dish), dish.getId());
    }
}