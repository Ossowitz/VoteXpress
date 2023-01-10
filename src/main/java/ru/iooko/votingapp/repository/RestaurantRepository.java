package ru.iooko.votingapp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.iooko.votingapp.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantRepository {

    private final CrudRestaurantRepository repository;

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.findAllRestaurantWithMenuWithDishesWithVotesByDateIsNow(LocalDate.now());
    }
}