package com.example.demo.controller;

import com.example.demo.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external-users")
public class ExternalUserController {

    private final ExternalUserService externalUserService;

    @Autowired
    public ExternalUserController(ExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }

    @GetMapping("/fetch-and-save")
    public String fetchAndSaveExternalUsers() {
        externalUserService.fetchAndSaveExternalUsers();
        return "External users fetched and saved successfully.";
    }
}