package ru.iooko.votingapp.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

public interface PersistableId extends Persistable<Integer> {
    @Override
    Integer getId();

    void setId(Integer id);

    @Override
    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    default int id() {
        Assert.notNull(getId(), "Entity must has valid id");
        return getId();
    }
}