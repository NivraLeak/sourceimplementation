package com.acme.ideogo.resource;

import com.acme.ideogo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MTaskResource extends AuditModel {
    private Long id;
    private String name;
    private String description;
    private Date deliveryDate;

}
