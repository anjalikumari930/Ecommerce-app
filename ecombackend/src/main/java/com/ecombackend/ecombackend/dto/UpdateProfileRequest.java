package com.ecombackend.ecombackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {
    private String email; // Used to identify the user
    private String name;
    private String phone;
    private String address;
}
