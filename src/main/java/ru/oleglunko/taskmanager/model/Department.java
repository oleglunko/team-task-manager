package ru.oleglunko.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Department extends BaseEntity {

    @Size(max = 128)
    @NotBlank
    private String name;

    @Size(min = 4, max = 20)
    @NotBlank
    private String contact;

    @Size(max = 100)
    @NotBlank
    private String head;

    @ManyToOne
    @NotNull
    private Company company;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Employee> employees;

    public Department(Integer id, String name, String contact, String head) {
        super(id);
        this.name = name;
        this.contact = contact;
        this.head = head;
    }
}
