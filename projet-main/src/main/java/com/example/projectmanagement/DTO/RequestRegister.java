package com.example.projectmanagement.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegister {
    private String password;
    private String username;
    private String userLastName;
    private String email;
    private byte[] profilePicture;
    private Long phoneNumber;
    private String RoleName;





}
