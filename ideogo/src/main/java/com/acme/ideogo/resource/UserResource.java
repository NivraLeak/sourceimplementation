package com.acme.ideogo.resource;

import com.acme.ideogo.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Long id;

    private String email;


    private String password;


    //private String sex;
//
//
    //private String occupation;
//
    //private String experience;
//
}
