package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.model.Restaurant;
import ru.iooko.votingapp.repository.RestaurantRepository;

import java.util.List;

import static org.springframework.util.Assert.notNull;
import static ru.iooko.votingapp.util.validation.ValidationUtil.checkNotFoundWithId;

@Service("restaurantService")
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public Restaurant create(Restaurant restaurant) {
        notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant update(Restaurant restaurant) {
        notNull(restaurant, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }
}