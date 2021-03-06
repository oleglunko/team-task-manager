package ru.oleglunko.taskmanager;

import ru.oleglunko.taskmanager.MatcherFactory.Matcher;
import ru.oleglunko.taskmanager.model.Employee;

import java.util.List;

import static ru.oleglunko.taskmanager.model.Role.ADMIN;
import static ru.oleglunko.taskmanager.model.Role.MANAGER;
import static ru.oleglunko.taskmanager.model.Role.USER;

public class EmployeeTestData {

    public static final Matcher<Employee> EMPLOYEE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Employee.class,
            "createdTasks", "performingTasks", "company", "department", "password");

    public static final int EMPLOYEE_ID = 100_007;
    public static final int NOT_FOUND_ID = 150;
    public static final int DEPARTMENT_ID = 100_001;

    public static final Employee employee1 = new Employee(EMPLOYEE_ID, "Спесивцев", "Сергей", "Андреевич", "spesivcev-sa", "12345", "Геолог", USER);
    public static final Employee employee2 = new Employee(EMPLOYEE_ID + 1, "Плеханов", "Андрей", "Дмитриевич", "plehanov-ad", "11111", "Ведущий геолог", USER);
    public static final Employee employee3 = new Employee(EMPLOYEE_ID + 2, "Антонов", "Сергей", "Борисович", "antonov-sb", "22222", "Начальник партии", MANAGER, USER);
    public static final Employee employee4 = new Employee(EMPLOYEE_ID + 3, "Прохоров", "Антон", "Николаевич", "prohorov-an", "33333", "Главный геолог партии", USER);
    public static final Employee employee5 = new Employee(EMPLOYEE_ID + 4, "Прозорова", "Юлия", "Евгеньевна", "prozorova-ye", "44444", "Геолог 1 категории", USER);
    public static final Employee employee6 = new Employee(EMPLOYEE_ID + 5, "Панова", "Мария", "Александровна", "panova-ma", "55555", "Геолог 2 категории", USER);
    public static final Employee employee7 = new Employee(EMPLOYEE_ID + 6, "Евгеньев", "Олег", "Сергеевич", "evgenev-os", "aAaaa", "Геолог 2 категории", USER);
    public static final Employee employee8 = new Employee(EMPLOYEE_ID + 7, "Крылов", "Михаил", "Николаевич", "krylov-mn", "123123", "Начальник партии", MANAGER, USER);
    public static final Employee employee9 = new Employee(EMPLOYEE_ID + 8, "Храмова", "Алина", "Сергеевна", "hramova-as", "12Abv12", "Геолог 1 категории", USER);
    public static final Employee employee10 = new Employee(EMPLOYEE_ID + 9, "Дмитриев", "Алексей", "Иванович", "dmitriev-ai", "54244", "Ведущий геолог", USER);
    public static final Employee employee11 = new Employee(EMPLOYEE_ID + 10, "Михайлова", "Нина", "Александровна", "mihaylova-na", "mih124", "Техник-геолог", USER);
    public static final Employee employee12 = new Employee(EMPLOYEE_ID + 11, "Строев", "Анатолий", "Глебович", "stroev-ag", "15265", "Начальник отдела", MANAGER, ADMIN, USER);
    public static final Employee employee13 = new Employee(EMPLOYEE_ID + 12, "Сержантов", "Василий", "Федорович", "serzhantov-vf", "vasfed", "Специалист 1 категории", ADMIN, USER);
    public static final Employee employee14 = new Employee(EMPLOYEE_ID + 13, "Внуков", "Матвей", "Александрович", "vnukov-ma", "1234556", "Специалист по КБ", ADMIN, USER);

    public static final List<Employee> allEmployees = List.of(employee3, employee14, employee10, employee7, employee8, employee11, employee6, employee2, employee5, employee4, employee13, employee1, employee12, employee9);
    public static final List<Employee> departmentEmployees = List.of(employee3, employee6, employee2, employee5, employee4, employee1);

    public static Employee getNew() {
        return new Employee(null, "Лунько", "Олег", "Евгеньевич", "lunko-oe", "l1294", "Геолог 1 категории", USER);
    }

    public static Employee getUpdated() {
        return new Employee(EMPLOYEE_ID, "Спесивцев", "Сергей", "Андреевич", "spesivcev-sa", "12345", "Геолог 2 категории", USER);
    }
}