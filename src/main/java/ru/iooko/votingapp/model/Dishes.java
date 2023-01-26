package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "dish_id"},
                name = "dishes_unique_menu_dish_idx"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dishes extends AbstractBaseEntity {

    @Column(name = "price", nullable = false, columnDefinition = "int default 0")
    @Range(max = 100_000)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonManagedReference // https://stackoverflow.com/questions/61840804/spring-boot-jpa-bidirectional-infinite-cycle-issue
    @ToString.Exclude
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @NotNull
    private Dish dish;

    public Dishes(Dish dish, Integer price) {
        this.dish = dish;
        this.price = price;
    }

    public Dishes(Integer id, Integer price, Dish dish) {
        super(id);
        this.price = price;
        this.dish = dish;
    }
}