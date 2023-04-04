package ua.weeding.core.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

//    @EmailUserValid(message = "Не правильний емейл")
//    @EmailAlreadyExists(message = "Користувач з такий емейлом вже існує")
    private String email;

    @Size(min = 8,message = "Пароль повинен містити мінімум 8 знаків")
    private String password;

    private String confirmPassword;
}
