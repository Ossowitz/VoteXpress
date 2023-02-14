package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.repository.MenuRepository;

import static java.time.LocalDate.now;
import static org.springframework.util.Assert.notNull;
import static ru.iooko.votingapp.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository repository;

    public Menu get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Menu getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id), id);
    }

    public Menu create(Menu menu) {
        notNull(menu, "Menu must not be null");
        menu.setDate(now());
        return repository.save(menu);
    }
}