package ru.oleglunko.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Task extends BaseEntity {

    @Size(min = 5, max = 100)
    @NotBlank
    private String title;

    @ManyToOne
    @NotNull
    private Employee author;

    @ManyToOne
    @NotNull
    private Employee performer;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;

    @NotNull
    private String description;

    public Task(Integer id, String title, LocalDateTime deadline, String description) {
        super(id);
        this.title = title;
        this.deadline = deadline;
        this.description = description;
    }
}
