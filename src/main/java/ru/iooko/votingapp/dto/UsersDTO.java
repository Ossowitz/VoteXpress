package ru.iooko.votingapp.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.iooko.votingapp.util.PersistableId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UsersDTO extends AbstractNamedDTO implements PersistableId {

    @Email
    @NotBlank
    @Size(min = 2, max = 128)
    String email;

    @NotBlank
    @Size(min = 8, max = 128)
    String password;

    public UsersDTO(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }
}