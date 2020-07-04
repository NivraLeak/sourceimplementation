package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class MTask extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private ProjectSchedule projectSchedule;
}
