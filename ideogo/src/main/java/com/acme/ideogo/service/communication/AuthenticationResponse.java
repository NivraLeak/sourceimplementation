package com.acme.ideogo.service.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private String username;
    private String token;
}
