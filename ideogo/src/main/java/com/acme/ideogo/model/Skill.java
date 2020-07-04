package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill extends AuditModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String DegreesRequirement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)/// cuando se instancia un comment, no tiene valor inicial --> lazy
    @JoinColumn(name = "tag_id", nullable = false)// fk a tabla posts
    @OnDelete(action = OnDeleteAction.CASCADE)//si borro un post, se van todos los comments en ese ost
    @JsonIgnore// si se pasa a formato json no considere esa columna
    private Tag tag;

}
