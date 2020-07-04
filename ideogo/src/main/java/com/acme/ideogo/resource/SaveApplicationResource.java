package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveApplicationResource {
    @NotNull
    //@NotBlank
    private int orderRequest;

    //@NotNull
    //@NotBlank
    public String state;
}
