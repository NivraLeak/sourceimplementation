package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@NotBlank
    public Date Date_time ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)/// cuando se instancia un comment, no tiene valor inicial --> lazy
    @JoinColumn(name = "project_schedule__id", nullable = false)// fk a tabla posts
    @OnDelete(action = OnDeleteAction.CASCADE)//si borro un post, se van todos los comments en ese ost
    @JsonIgnore// si se pasa a formato json no considere esa columna
    private ProjectSchedule projectSchedule;

}
