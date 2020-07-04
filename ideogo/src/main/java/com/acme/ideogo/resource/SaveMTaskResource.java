package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class SaveMTaskResource {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
}
