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
public class SaveUserResource {
    @NotNull
    @Size(max = 40)
    @NaturalId
    private String email;

    @NotNull
    @Size(max = 20)
    @NaturalId
    private String password;

    //@NotNull
    //@Size(max = 4)
    //@NaturalId
    //private String sex;
//
    //@NotNull
    //@Size(max = 20)
    //@NaturalId
    //private String occupation;
//
    //@NotNull
    //@NotBlank
    //@Size(max = 100)
    //private String experience;
}