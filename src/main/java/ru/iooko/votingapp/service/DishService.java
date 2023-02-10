package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.model.Dish;
import ru.iooko.votingapp.repository.DishRepository;

import java.util.List;

import static org.springframework.util.Assert.*;
import static ru.iooko.votingapp.util.validation.ValidationUtil.*;

@Service("dishService")
@AllArgsConstructor
public class DishService {

    private final DishRepository repository;

    public Dish create(Dish dish) {
        notNull(dish, "dish must not be null");
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Dish> getAll() {
        return repository.getAll();
    }

    public Dish update(Dish dish) {
        notNull(dish, "dish must not be null");
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }
}