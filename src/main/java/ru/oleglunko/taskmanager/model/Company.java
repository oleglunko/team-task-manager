package ru.oleglunko.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonIgnoreProperties({"departments", "employees"})
@Entity
public class Company extends BaseEntity {

    @Size(max = 128)
    @NotBlank
    private String name;

    @Size(max = 200)
    @NotBlank
    private String physicalAddress;

    @Size(max = 200)
    @NotBlank
    private String legalAddress;

    @Size(max = 100)
    @NotBlank
    private String head;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Department> departments;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private List<Employee> employees;

    public Company(Integer id, String name, String physicalAddress, String legalAddress, String head) {
        super(id);
        this.name = name;
        this.physicalAddress = physicalAddress;
        this.legalAddress = legalAddress;
        this.head = head;
    }
}
