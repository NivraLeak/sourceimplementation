package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveGoalResource {
    @NotNull
    @Size(max = 100)
    @NaturalId
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;
}