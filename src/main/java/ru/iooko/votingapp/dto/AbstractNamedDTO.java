package ru.iooko.votingapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractNamedDTO extends AbstractBaseDTO {

    @NotBlank
    @Size(min = 2, max = 128)
    protected String name;

    public AbstractNamedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}