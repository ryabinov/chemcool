package com.chemcool.school.registration.web.api.dto;

import com.chemcool.school.registration.annotations.ValidPassword;
import com.chemcool.school.registration.domain.RegisterUserAccountRole;
import com.chemcool.school.registration.domain.RegisterUserAccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private LocalDate birthday;

    @NotBlank
    private String gender;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    @Enumerated(EnumType.STRING)
    private RegisterUserAccountType type;

    @Enumerated(EnumType.STRING)
    private RegisterUserAccountRole role;

    private String verificationCode;

    private boolean enabled;

    public String getFullName() {
        return this.name + " " + this.surname;
    }
}
