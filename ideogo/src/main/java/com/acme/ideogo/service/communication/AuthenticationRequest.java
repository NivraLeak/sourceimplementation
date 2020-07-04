package com.acme.ideogo.service.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
