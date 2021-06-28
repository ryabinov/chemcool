package com.chemcool.school.lesson.theory.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
public class ChemTheoryEvent {
    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_author")
    private String eventAuthor;

    @Column(name = "event_occurring_context")
    private String eventOccurringContext;

    @Column(name = "event_occurring_context_time")
    private LocalDateTime eventOccurringContextTime;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "version")
    private String version;

    @Column(name = "event_entity_id")
    private String EventEntityId;

    @Type(type = "jsonb")
    @Column(name = "event_payload", columnDefinition = "jsonb")
    private ChemTheory eventPayload;

    public static ChemTheoryEvent createEvent(ChemTheory theory, ChemTheoryEventType type) {
        return new ChemTheoryEvent(
                UUID.randomUUID().toString(),
                "123",
                "ChemistryTheoryEvent",
                LocalDateTime.now(),
                type.toString(),
                "1.0",
                theory.getTheoryId(),
                theory
        );
    }
}