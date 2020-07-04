package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class MembershipResource {
    private Date start_at;
    private Date end_at;
    private String Name;
}
