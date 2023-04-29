package com.example.projectmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegister {
    private String password;
    private String username;
    private String userLastName;
    private MultipartFile profilePicture;
    private String email;
    private String title;
    private Long phoneNumber;
    private String RoleName;





}
