package com.example.demo.service;

import com.example.demo.dto.ExternalUserDto;
import com.example.demo.model.ExternalUser;
import com.example.demo.repository.ExternalUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalUserService {
    private final RestTemplate restTemplate;
    private final ExternalUserRepository externalUserRepository;

    public ExternalUserService(RestTemplate restTemplate, ExternalUserRepository externalUserRepository) {
        this.restTemplate = restTemplate;
        this.externalUserRepository = externalUserRepository;
    }

    public void fetchAndSaveExternalUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        ExternalUserDto[] externalUserDtos = restTemplate.getForObject(url, ExternalUserDto[].class);
        List<ExternalUserDto> externalUserDtoList = Arrays.asList(externalUserDtos);

        for (ExternalUserDto externalUserDto : externalUserDtoList) {
            ExternalUser externalUser = externalUserRepository.findByUsername(externalUserDto.getUsername())
                    .orElse(new ExternalUser());

            externalUser.setName(externalUserDto.getName());
            externalUser.setUsername(externalUserDto.getUsername());
            externalUser.setEmail(externalUserDto.getEmail());
            // Map other fields as necessary

            externalUserRepository.save(externalUser);
        }
    }
}