package ru.iooko.votingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Menu;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Query("FROM Menu m LEFT JOIN FETCH m.dishes md LEFT JOIN FETCH md.dish WHERE m.id =: id")
    Menu getWithDishes(@Param("id") int id);
}