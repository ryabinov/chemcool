package com.chemcool.school.lesson.domain.matches;

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
public class ChemMatchingTaskEvent {

    @Id
    private String chemistryMatchingTaskEventId;
    private String chemistryMatchingTaskEventAuthorId;
    private String chemistryMatchingTaskEventOccurringContext;
    private LocalDateTime chemistryMatchingTaskEventOccurringContextTime;
    private String chemistryMatchingTaskEventType;
    private String version;
    private String chemistryMatchingTaskEventEntityId;

    @Type(type = "jsonb")
    @Column(name = "chemistry_matching_task_event_payload", columnDefinition = "jsonb")
    private ChemMatchingTask chemMatchingTaskEventPayload;


    public static ChemMatchingTaskEvent createEvent(ChemMatchingTask task, String authorId, ChemMatchingTaskEventType type){
        return new ChemMatchingTaskEvent(
                UUID.randomUUID().toString(),
                authorId,
                "ChemistryMatchingTaskEvent",
                LocalDateTime.now(),
                type.toString(),
                "1.0",
                task.getTaskId(),
                task
        );
    }

}
