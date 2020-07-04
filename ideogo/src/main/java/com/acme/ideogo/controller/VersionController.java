package com.acme.ideogo.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RestController
@RequestMapping("/api")
public class VersionController {
    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}

