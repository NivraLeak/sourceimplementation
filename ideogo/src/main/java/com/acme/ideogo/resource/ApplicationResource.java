package com.acme.ideogo.resource;

import com.acme.ideogo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApplicationResource extends AuditModel {
    private Long id;
    private int orderRequest;
    private String state;
}
