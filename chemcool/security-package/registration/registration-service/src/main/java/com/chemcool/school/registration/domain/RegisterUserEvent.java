package com.chemcool.school.registration.domain;

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
@Entity
@Table(name = "users_event")
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
public class RegisterUserEvent {
    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "occurring_context")
    private String occurringContext;

    @Column(name = "occurring_context_time")
    private LocalDateTime occurringContextTime;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private RegisterUserEventType eventType;

    @Column(name = "event_version")
    private String eventVersion;

    @Column(name = "entity_id")
    private String entityId;

    @Type(type = "jsonb")
    @Column(name = "payload", columnDefinition = "jsonb")
    private RegisterUser payload;

    public static RegisterUserEvent createEvent(RegisterUser registerUser, RegisterUserEventType type) {
        return new RegisterUserEvent(
                UUID.randomUUID().toString(),
                registerUser.getEmail(),
                "UserRegistrationEvent",
                LocalDateTime.now(),
                type,
                "1.0",
                registerUser.getId(),
                registerUser
        );
    }
}
