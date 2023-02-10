package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.iooko.votingapp.model.Restaurant;
import ru.iooko.votingapp.repository.RestaurantRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

@Service("restaurantService")
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}