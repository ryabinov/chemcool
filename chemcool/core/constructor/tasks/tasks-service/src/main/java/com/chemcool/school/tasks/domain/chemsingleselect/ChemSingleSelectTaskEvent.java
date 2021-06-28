package com.chemcool.school.tasks.domain.chemsingleselect;

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
@Table(name = "chemistry_single_select_task_event")
public class ChemSingleSelectTaskEvent {

    @Id
    @Column(name = "id")
    private String TaskEventId;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "occuring_context")
    private String occuringContext;

    @Column(name = "occuring_context_time")
    private LocalDateTime occuringContextTime;

    @Column(name = "event_type")
    private ChemTaskEventType eventType;

    @Column(name = "event_version")
    private String eventVersion;

    @Column(name = "entity_id")
    private String entityId;

    @Type(type = "jsonb")
    @Column(name = "payload", columnDefinition = "jsonb")
    private ChemSingleSelectTask payload;

    public static ChemSingleSelectTaskEvent createEvent(ChemSingleSelectTask task, String authorId, ChemTaskEventType eventType){
//        ChemSingleSelectTask task = ChemSingleSelectTask.createChemistrySingleSelectTask(example);
        return new ChemSingleSelectTaskEvent(
                UUID.randomUUID().toString(),
                authorId,
                "ChemSingleSelectTaskEvent",
                LocalDateTime.now(),
                eventType,
                "1.0",
                task.getTaskId(),
                task
        );
    }
}