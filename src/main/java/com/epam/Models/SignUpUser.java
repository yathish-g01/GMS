package com.epam.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUser {
    private String fullName;
    private String password;
    private String email;
    private String target;
    private String preferableActivity;
}
