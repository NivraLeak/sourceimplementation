package com.acme.ideogo.resource;

import com.acme.ideogo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileResource extends AuditModel {
    private Long id;
    private String name;
    private String gender;
    private String occupation;

    private int age;
    private String typeUser;
}

