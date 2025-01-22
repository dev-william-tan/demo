package com.example.demo.dto;

import lombok.Data;

@Data
public class ExternalUserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressDto address;
    private String phone;
    private String website;
    private CompanyDto company;

    @Data
    public static class AddressDto {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private GeoDto geo;

        @Data
        public static class GeoDto {
            private String lat;
            private String lng;
        }
    }

    @Data
    public static class CompanyDto {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}