package ru.iooko.votingapp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Dish;

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
}