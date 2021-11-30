package ru.oleglunko.taskmanager;

import ru.oleglunko.taskmanager.MatcherFactory.Matcher;
import ru.oleglunko.taskmanager.model.Department;

import java.util.List;

public class DepartmentTestData {

    public static final Matcher<Department> DEPARTMENT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Department.class, "company", "employees");

    public static final int DEPARTMENT_ID = 100_001;
    public static final int NOT_FOUND_ID = 150;

    public static final Department department1 = new Department(DEPARTMENT_ID,"Байкитская партия", "1-69", "Атонов Сергей Борисович");
    public static final Department department2 = new Department(DEPARTMENT_ID + 1,"Восточно-Саянская партия", "1-55", "Крылов Михаил Николаевич");
    public static final Department department3 = new Department(DEPARTMENT_ID + 2,"Приенисейская партия", "1-23", "Лягушев Глеб Петрович");
    public static final Department department4 = new Department(DEPARTMENT_ID + 3,"Геохимическая партия", "1-43", "Петрова Мария Сергеевна");
    public static final Department department5 = new Department(DEPARTMENT_ID + 4,"Отдел информационных технологий", "1-49", "Строев Анатолий Глебович");
    public static final Department department6 = new Department(DEPARTMENT_ID + 5,"Геофизическая партия", "1-13", "Иванов Александр Иванович");

    public static final List<Department> departments = List.of(department1, department2, department6, department4, department5, department3);

    public static Department getNew() {
        return new Department(null, "Вороговская партия", "1-98", "Сергеев Руслан Ренатович");
    }

    public static Department getUpdated() {
        return new Department(DEPARTMENT_ID, "Байкитская партия", "1-69", "Петров Сергей Юрьевич");
    }
}
