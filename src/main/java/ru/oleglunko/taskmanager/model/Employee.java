package ru.oleglunko.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;
import ru.oleglunko.taskmanager.View;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
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
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonIgnoreProperties({"company", "createdTasks", "performingTasks"})
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

    @Size(max = 100)
    @NotBlank
    private String login;

    @Size(min = 5, max = 100)
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean enabled = true;

    @Size(max = 50)
    @NotBlank
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(groups = View.Persist.class)
    @ToString.Exclude
    private Company company;

    @ManyToOne
    @NotNull
    @ToString.Exclude
    private Department department;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "performer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Task> performingTasks;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @Column(name = "role")
    private Set<Role> roles;

    public Employee(Integer id, String lastName, String firstName, String patronymic, String login, String password, String position, Role role, Role... roles) {
        this(id, lastName, firstName, patronymic, login, password, true, position, EnumSet.of(role, roles));
    }

    public Employee(Integer id, String lastName, String firstName, String patronymic, String login, String password, boolean enabled, String position, Collection<Role> roles) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.position = position;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
