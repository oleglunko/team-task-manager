package ru.oleglunko.taskmanager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import ru.oleglunko.taskmanager.model.Employee;

import java.io.Serial;

@Getter
@Setter
public class AuthorizedEmployee extends User {

    @Serial
    private static final long serialVersionUID = 1L;

    private Employee employee;

    public AuthorizedEmployee(Employee employee) {
        super(employee.getLogin(), employee.getPassword(), employee.isEnabled(), true, true, true, employee.getRoles());
        setEmployee(employee);
    }

    public int getId() {
        return employee.getId();
    }

    @Override
    public String toString() {
        return employee.toString();
    }
}
