package ru.oleglunko.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Employee extends BaseEntity {

    @Size(max = 50)
    @NotBlank
    private String lastName;

    @Size(max = 50)
    @NotBlank
    private String firstName;

    @Size(max = 50)
    @NotBlank
    private String patronymic;

    @Size(max = 50)
    @NotBlank
    private String position;

    @ManyToOne
    @NotNull
    private Company company;

    @ManyToOne
    @NotNull
    private Department department;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "performer")
    @ToString.Exclude
    private List<Task> performingTasks;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Employee(Integer id, String lastName, String firstName, String patronymic, String position) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.position = position;
    }
}
