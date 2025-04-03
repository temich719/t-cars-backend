package com.tcars.maincarsservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private MultipartFile avatar;

}
