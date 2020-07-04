package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String occupation;

    @NotNull
    //@NotBlank
    private int age;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String typeuser;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profiles_tags",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonIgnore
            List<Tag> tags;


    @OneToOne
    private User user;



}

