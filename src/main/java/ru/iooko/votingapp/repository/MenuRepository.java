package ru.iooko.votingapp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Menu;

@Repository
@AllArgsConstructor
public class MenuRepository {

    private final CrudMenuRepository repository;

    @Transactional
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public Menu getWithDishes(int id) {
        return repository.getWithDishes(id);
    }

    public Menu get(int id) {
        return repository.findById(id).orElse(null);
    }
}