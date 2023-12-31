package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.iooko.votingapp.util.accessory.PersistableId;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "allJoined",
                attributeNodes = {
                        @NamedAttributeNode(value = "menues", subgraph = "menues-sub")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "menues-sub",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "dishes", subgraph = "dishes-sub"),
                                        @NamedAttributeNode(value = "votes"),
                                }
                        ),
                        @NamedSubgraph(
                                name = "dishes-sub",
                                attributeNodes = @NamedAttributeNode("dish")
                        )
                }
        )
})
@Table(name = "restaurant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"},
                name = "restaurants_unique_name_idx"))
@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity implements PersistableId {

    @JsonIgnore
    @JsonBackReference // https://stackoverflow.com/questions/31319358/jsonmanagedreference-vs-jsonbackreference
    @OneToMany(mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Menu> menues;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(), restaurant.getName());
        this.menues = restaurant.getMenues();
    }
}