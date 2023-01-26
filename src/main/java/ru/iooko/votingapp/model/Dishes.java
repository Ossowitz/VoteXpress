package ru.iooko.votingapp.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

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
    private Menu menu;
}