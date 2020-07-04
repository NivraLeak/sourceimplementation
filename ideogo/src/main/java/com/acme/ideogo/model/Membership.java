package com.acme.ideogo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "memberships")
@Getter
@Setter
public class Membership extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    //@NotBlank
    private Date start_at;

    @NotNull
    // @NotBlank
    private Date end_at;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String Name;

}
