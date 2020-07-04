package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveRequirementResource {

    @NotNull
    @NotBlank
    @Size(max = 100)

    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;
}
