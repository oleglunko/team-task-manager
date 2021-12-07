package ru.oleglunko.taskmanager.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.oleglunko.taskmanager.model.Employee;

@UtilityClass
public class EmployeeUtil {

    public static Employee prepareToSave(Employee employee, PasswordEncoder passwordEncoder) {
        String password = employee.getPassword();
        employee.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        employee.setLogin(employee.getLogin().toLowerCase());
        return employee;
    }
}
