package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.repository.DishRepository;

@Service("dishService")
@AllArgsConstructor
public class DishService {

    private final DishRepository repository;
}