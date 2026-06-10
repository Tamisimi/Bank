package org.example.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private boolean isKyc;
}