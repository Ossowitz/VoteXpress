package ru.iooko.votingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.iooko.votingapp.model.Votes;

import java.time.LocalDate;
import java.util.Optional;

public interface CrudVoteRepository extends JpaRepository<Votes, Integer> {

    @Query("SELECT v FROM Votes v JOIN v.menu m WHERE v.user.id =: user_id AND m.date =: today")
    Optional<Votes> getWithMenuForUserOnToday(@Param("user_id") int userId, @Param("today")LocalDate today);

    @Query("SELECT v FROM Votes v JOIN FETCH v.user JOIN FETCH v.menu WHERE v.id =: vote_id")
    Optional<Votes> getByIdWithMenuWithUser(@Param("vote_id") int voteId);
}