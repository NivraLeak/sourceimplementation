package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveResourceResource {

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String Unit;

    @NotNull
    //@NotBlank
    private float Quantity;


}
