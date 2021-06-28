package com.chemcool.school.auth.service.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
@Table(name = "users_event")
public class RegisterUserEvent {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_author_id")
    private String authorId;

    @Column(name = "event_occurring_context")
    private String occurringContext;

    @Column(name = "event_occurring_context_time")
    private LocalDateTime occurringContextTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private RegisterUserEventType eventType;

    @Column(name = "event_version")
    private String eventVersion;

    @Column(name = "event_entity_id")
    private String entityId;

    @Type(type = "jsonb")
    @Column(name = "event_payload", columnDefinition = "jsonb")
    private RegisterUser payload;

    public static RegisterUserEvent createEvent(RegisterUser registerUser, RegisterUserEventType type) {
        return new RegisterUserEvent(
                UUID.randomUUID().toString(),
                registerUser.getEmail(),
                "UserRegistrationSocialEvent",
                LocalDateTime.now(),
                type,
                "1.0",
                registerUser.getId(),
                registerUser
        );
    }
}
