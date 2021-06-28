package com.chemcool.school.answers.domain.equation;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
@Table(name = "chemistry_equations_task_event")
public class ChemEquationsTaskEvent {

    @Id
    @Column(name = "event_uuid")
    private String eventId;

    @Column(name = "event_author_id")
    private String eventAuthorId;

    @Column(name = "event_occuring_context")
    private String eventOccuringContext;

    @Column(name = "event_occuring_context_time")
    private LocalDateTime eventOccuringContextTime;

    @Column(name = "event_type")
    private ChemEquationsTaskEventType eventType;

    @Column(name = "event_version")
    private String eventVersion;

    @Column(name = "event_entity_id")
    private String eventEntityId;

    @Type(type = "jsonb")
    @Column(name = "event_payload", columnDefinition = "jsonb")
    private ChemEquationsTask eventPayload;
}
