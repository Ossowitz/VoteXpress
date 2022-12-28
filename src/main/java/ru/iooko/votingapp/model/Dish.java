package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.iooko.votingapp.util.accessory.PersistableId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "dish",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"},
                name = "dish_unique_name_idx"))
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity implements PersistableId {

    @OneToMany(mappedBy = "dish")
    @JsonIgnore
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dishes> dishes;

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(Dish dish) {
        super(dish.getId(), dish.getName());
        this.dishes = dish.getDishes();
    }
}