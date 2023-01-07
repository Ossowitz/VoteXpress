package ru.iooko.votingapp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Dish;

import java.util.List;

@Repository
@AllArgsConstructor
public class DishRepository {

    private final CrudDishRepository repository;

    @Transactional
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }
}