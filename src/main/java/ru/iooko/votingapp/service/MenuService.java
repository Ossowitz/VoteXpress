package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.repository.MenuRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository repository;

    public Menu get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public Menu getWithDishes(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithDishes(id), id);
    }
}