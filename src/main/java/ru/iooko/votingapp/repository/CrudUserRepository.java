package ru.iooko.votingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Users;

import java.util.Optional;

public interface CrudUserRepository extends JpaRepository<Users, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Users u WHERE u.id =: id")
    int delete(@Param("id") int id);

    @Query("FROM Users u LEFT JOIN FETCH u.roles r ")
    Optional<Users> getByEmail(@Param("email") String email);
}