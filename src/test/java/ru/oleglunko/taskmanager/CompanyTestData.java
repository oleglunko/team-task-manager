package ru.oleglunko.taskmanager;

import ru.oleglunko.taskmanager.MatcherFactory.Matcher;
import ru.oleglunko.taskmanager.model.Company;

public class CompanyTestData {

    public static final Matcher<Company> COMPANY_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Company.class,
            "employees", "departments");

    public static final int COMPANY_ID = 100_000;
    public static final int NOT_FOUND_ID = 150;
    public static final int EMPLOYEE_ID = 100_007;

    public static final Company company = new Company(COMPANY_ID, "Сибирская геологическая компания",
            "660077, Красноярский край, г. Красноярск, ул. Весны, д. 5, оф. 122",
            "660077, Красноярский край, г. Красноярск, ул. Весны, д. 5, оф. 122", "Стрельцов Александр Владимирович");

    public static Company getNew() {
        return new Company("Новая компания", "Новый физический адрес", "Новый юридический адрес", "Новый руководитель");
    }

    public static Company getUpdated() {
        return new Company(COMPANY_ID, "Обновленное название", "Обновленный физический адрес",
                "Обновленный юридический адрес", "Обновленный руководитель");
    }
}
