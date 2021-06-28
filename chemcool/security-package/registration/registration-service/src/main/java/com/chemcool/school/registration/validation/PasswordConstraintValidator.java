package com.chemcool.school.registration.validation;

import com.chemcool.school.registration.annotations.ValidPassword;
import lombok.SneakyThrows;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {


        Properties props = new Properties();
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("passay.properties");
        props.load(inputStream);
        MessageResolver resolver = new PropertiesMessageResolver(props);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                // длина пароля от 8 до 16 символов
                new LengthRule(8, 16),

                // должно быть от одной заглавной буквы в пароле
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // должно быть от одной строчной буквы в пароле
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // должно быть от одной цифры в пароле
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // должно быть от одного символа в пароле
                new CharacterRule(EnglishCharacterData.Special, 1),

                // без пробелов
                new WhitespaceRule(),

                // отклоняет пароли, содержащие последовательность из >= 5 буквенных символов (например, abcdef)
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),

                // отклоняет пароли, содержащие последовательность из >= 5 цифр (например, 12345)
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),

                //отклоняет пароли, содержащие последовательность клавиатуры QWERTY >= 5  (например, qwerty)
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false)
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
