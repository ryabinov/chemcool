package com.chemcool.school.registration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class RegisterUser {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender")
    private String gender;


    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private RegisterUserAuthProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RegisterUserAccountType type;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RegisterUserAccountRole role;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    public static RegisterUser createUser(
            RegisterUserExample example
    ) {
        return new RegisterUser(
                example.getUserExampleId(),
                example.getUserExampleName(),
                example.getUserExampleSurname(),
                example.getUserExampleBirthday(),
                example.getUserExampleGender(),
                example.getUserExamplePhone(),
                example.getUserExampleEmail(),
                example.getUserExamplePassword(),
                example.getImageUrl(),
                example.getUserExampleAuthProvider(),
                example.getUserExampleProviderId(),
                example.getUserExampleType(),
                example.getUserExampleRole(),
                example.getUserExampleVerificationCode(),
                example.isUserExampleEnabled(),
                example.getUserExampleResetPasswordToken()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterUser registerUser = (RegisterUser) o;
        return id.equals(registerUser.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
