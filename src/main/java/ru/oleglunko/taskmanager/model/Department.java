package ru.oleglunko.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.oleglunko.taskmanager.View;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@JsonIgnoreProperties({"company", "employees"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
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
