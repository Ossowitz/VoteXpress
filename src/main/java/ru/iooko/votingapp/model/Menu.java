package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menu",
        uniqueConstraints = @UniqueConstraint(columnNames = {"date", "restaurant_id"},
                name = "menu_unique_date_restaurant_idx"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Menu extends AbstractBaseEntity {

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference // https://stackoverflow.com/questions/61840804/spring-boot-jpa-bidirectional-infinite-cycle-issue
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dishes> dishes;

    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Votes> votes;

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Menu(Integer id, LocalDate date, Set<Dishes> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

//    https://javascopes.com/jpa-hibernate-synchronize-bidirectional-entity-associations-2ccfb961/
    public Menu(Restaurant restaurant, Set<Dishes> dishes) {
        this.restaurant = restaurant;
        dishes.forEach(d -> d.setMenu(this));
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "\nMenu{" +
               "date=" + (date == null ? "null" : date) +
               ",\n dishes=" + (dishes == null ? "null" : dishes) +
               ",\n votes=" + (votes == null ? "null" : votes) +
               ",\n id menu=" + id +
               ",\n id restaurant=" + (restaurant == null ? "null" : restaurant.id) +
               '}';
    }
}