package ru.iooko.votingapp.repository;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.iooko.votingapp.model.Users;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final static Sort SORT_BY_ID = Sort.by(Sort.Direction.ASC, "id");

    private final CrudUserRepository repository;

    @Transactional
    public Users save(Users user) {
        return repository.save(user);
    }

    @Transactional
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}
