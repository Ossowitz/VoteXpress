package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(
        columnNames = "name", name = "role_unique_name_idx"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Role extends AbstractNamedEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Roles> roles;

    public Role(Integer id, String name) {
        super(id, name);
    }
}