package ru.iooko.votingapp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.iooko.votingapp.model.Votes;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class VoteRepository {

    private final CrudVoteRepository repository;

    public Votes save(int id) {
        return repository.getByIdWithMenuWithUser(id).orElse(null);
    }

    public Votes getByIdWithMenuWithUser(int id) {
        return repository.getByIdWithMenuWithUser(id).orElse(null);
    }

    public Votes getForUser(int userId) {
        return repository.getWithMenuForUserOnToday(userId, LocalDate.now()).orElse(null);
    }
}