package ru.oleglunko.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    private Employee author;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    private Employee performer;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;

    @NotNull
    private String description;

    private boolean performed;

    private boolean accepted;

    public Task(Integer id, String title, LocalDateTime deadline, String description, boolean performed, boolean accepted) {
        super(id);
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        this.performed = performed;
        this.accepted = accepted;
    }
}
