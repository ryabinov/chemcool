package com.chemcool.school.registration.app;

import com.chemcool.school.registration.domain.*;
import com.chemcool.school.registration.repository.RegisterUserEventRepository;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import com.chemcool.school.registration.web.api.dto.RegisterUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {RegistrationApplication.class})
@AutoConfigureMockMvc
public class RegistrationControllerIntegrationTest extends ContainerConfigurationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    RegisterUserRepository repository;
    @Autowired
    RegisterUserEventRepository registerUserEventRepository;

    String nick = "Maverick";
    String name = "Tom";
    String surname = "Cruise";
    String gender = "MALE";
    String city = "Texas";
    String phone = "+18995556677";
    String email = "tom@mail.com";
    String password = "15975Aw!";

    /*RegisterUserDto registerUserDto = new RegisterUserDto(
            nick, name, surname, gender, city, phone, email, password
    );*/

   /* @Test
    @Order(4)
    @DisplayName("Передаем тестового юзера в Рест контроллер")
    public void SendRegisterUserToControllerTest() throws Exception {

        mockMvc.perform(
                post("/registration")
                        .content(objectMapper.writeValueAsString(registerUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());
    }*/

    @Test
    @Order(5)
    @DisplayName("Проверяем тестового юзера в БД")
    public void GetRegisterUserFromDB() {

        Iterable<RegisterUser> users = repository.findAll();

        for (RegisterUser user : users) {

            assertThat(name).isEqualTo(user.getName());
            assertThat(surname).isEqualTo(user.getSurname());
            assertThat(gender).isEqualTo(user.getGender());
            assertThat(phone).isEqualTo(user.getPhone());
            assertThat(email).isEqualTo(user.getEmail());
            assertThat(RegisterUserAccountRole.ROLE_USER_BASE).isEqualTo(user.getRole());
            assertThat(RegisterUserAccountType.BASE).isEqualTo(user.getType());

            System.out.println(user.toString());
        }
    }

    @Test
    @Order(6)
    @DisplayName("Получаем RegisterUserEvent из БД")
    public void GetRegisterUserEventFromDB() {

        Iterable<RegisterUserEvent> events = registerUserEventRepository.findAll();

        for (RegisterUserEvent r : events) {

            assertThat(r.getEventId()).isNotNull().isNotEmpty();
            assertThat(r.getAuthorId()).isNotNull().isNotEmpty().isEqualTo(nick);
            assertThat(r.getOccurringContext()).isNotNull().isNotEmpty();
            assertThat(r.getOccurringContextTime()).isNotNull();
            assertThat(r.getEventType()).isEqualTo(RegisterUserEventType.CREATE);
            assertThat(r.getEventVersion()).isNotNull().isNotEmpty();
            assertThat(r.getEntityId()).isEqualTo(r.getPayload().getId());
            assertThat(r.getEventVersion()).isNotNull().isNotEmpty();
            assertThat(r.getPayload()).isNotNull();

            System.out.println("RegisterUserEvent:");
            System.out.println("event_id: " + r.getEventId());
            System.out.println("author_id: " + r.getAuthorId());
            System.out.println("occurring_context: " + r.getOccurringContext());
            System.out.println("occurring_context_time: " + r.getOccurringContextTime());
            System.out.println("event_type: " + r.getEventType());
            System.out.println("event_version: " + r.getEventVersion());
            System.out.println("entity_id: " + r.getEntityId());
            System.out.println("payload: \n" + r.getPayload().toString());
        }
    }
}
