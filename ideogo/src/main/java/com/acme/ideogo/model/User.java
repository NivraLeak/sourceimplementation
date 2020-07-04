package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @NaturalId
    private String email;

    @NotNull
    @Size(max = 30)
    @NaturalId
    private String password;

    //@NotNull
    //@Size(max = 10)
    //@NaturalId
    //private String sex;
//
    //@NotNull
    //@Size(max = 30)
    //@NaturalId
    //private String occupation;
//
    //@NotNull
    //@NotBlank
    //@Size(max = 250)
    //private String experience;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_project",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @JsonIgnore
    List<Project> projects;
}
