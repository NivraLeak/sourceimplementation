package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
public class SaveMembershipResource {
    @NotNull
    //@NotBlank
    private Date start_at;

    @NotNull
    //@NotBlank
    private Date end_at;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String Name;
}
