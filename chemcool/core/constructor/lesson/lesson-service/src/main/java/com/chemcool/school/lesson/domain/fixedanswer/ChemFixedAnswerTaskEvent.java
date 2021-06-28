package com.chemcool.school.lesson.domain.fixedanswer;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
@Table(name = "chemistry_fixed_answer_event")
public class ChemFixedAnswerTaskEvent {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_author_id")
    private String eventAuthorId;

    @Column(name = "event_occuring_comtext")
    private String eventOccuringContext;

    @Column(name = "event_occuring_context_time")
    private LocalDateTime eventOccuringContextTime;

    @Column(name = "event_type")
    private ChemFixedAnswerTaskEventType eventType;

    @Column(name = "event_version")
    private String eventVersion;

    @Column(name = "event_entity_id")
    private String eventEntityId;

    @Type(type = "jsonb")
    @Column(name = "event_payload", columnDefinition = "jsonb")
    private ChemFixedAnswerTask eventPayload;

    public static ChemFixedAnswerTaskEvent createEvent(ChemFixedAnswerTask task, ChemFixedAnswerTaskEventType eventType){
        return new ChemFixedAnswerTaskEvent(
                UUID.randomUUID().toString(),
                "321",
                "ChemFixedAnswerTaskEvent",
                LocalDateTime.now(),
                eventType,
                "1.0",
                task.getTaskId(),
                task
        );
    }




}
