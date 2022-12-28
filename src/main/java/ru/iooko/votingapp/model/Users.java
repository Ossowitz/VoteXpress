package ru.iooko.votingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.iooko.votingapp.util.accessory.PersistableId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Users extends AbstractNamedEntity implements PersistableId {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(min = 2, max = 128)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank // cannot be empty
    @Size(min = 8, max = 128)
    // should only be used when serializing
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude // should not be included in the output
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // should only be used when deserializing
    private LocalDate registered = LocalDate.now();

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Votes> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Roles> roles;

    public Users(Integer id, String name, String email, String password) {
        this(id, name, email, password, true, LocalDate.now());
    }

    public Users(Integer id, String name, String email,
                 String password, boolean enabled, LocalDate registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
    }

    public Users(Integer id, String name, String email,
                 String password, boolean enabled,
                 LocalDate registered, Set<Roles> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public Users(Users user) {
        super(user.getId(), user.getName());
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.registered = user.getRegistered();
        this.roles = user.getRoles();
        this.votes = user.getVotes();
    }
}