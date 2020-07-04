package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveProfileResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String occupation;

    @NotNull
    //@NotBlank
    private int age;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String typeUser;
}