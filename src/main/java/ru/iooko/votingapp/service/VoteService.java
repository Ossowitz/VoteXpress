package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.model.Votes;
import ru.iooko.votingapp.repository.VoteRepository;
import ru.iooko.votingapp.util.validation.ValidationUtil;

@Service
@AllArgsConstructor
public class VoteService {

    private final UserService userService;
    private final MenuService menuService;
    private final VoteRepository voteRepository;

    public Votes getByWithMenuWithUser(int id) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.getByIdWithMenuWithUser(id), id);
    }
}