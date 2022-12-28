package ru.iooko.votingapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iooko.votingapp.exception.VoteNotAllowedUpdateException;
import ru.iooko.votingapp.model.Votes;
import ru.iooko.votingapp.repository.VoteRepository;
import ru.iooko.votingapp.util.accessory.DateTimeUtil;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {

    private final UserService userService;
    private final MenuService menuService;
    private final VoteRepository voteRepository;

    public Votes getByWithMenuWithUser(int id) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.getByIdWithMenuWithUser(id), id);
    }

    public Votes create(int userId, int menuId, LocalTime current, LocalTime constraint) {
        Votes votes = voteRepository.getForUser(userId);

        if (votes == null)
            return voteRepository.save(new Votes(menuService.get(menuId), userService.get(userId)));
        if (!current.isBefore(constraint))
            throw new VoteNotAllowedUpdateException("Vote can't be changed after " + DateTimeUtil.toString(constraint));

        votes.setMenu(menuService.get(menuId));
        return voteRepository.save(votes);
    }
}