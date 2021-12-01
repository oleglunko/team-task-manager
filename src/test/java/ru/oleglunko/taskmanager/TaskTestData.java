package ru.oleglunko.taskmanager;

import ru.oleglunko.taskmanager.MatcherFactory.Matcher;
import ru.oleglunko.taskmanager.model.Task;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class TaskTestData {

    public static final Matcher<Task> TASK_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Task.class, "author", "performer");

    public static final int TASK_ID = 100_021;
    public static final int PERFORMED_TASK_ID = 100_022;
    public static final int NOT_FOUND_ID = 150;
    public static final int EMPLOYEE_ID1 = 100_007;
    public static final int EMPLOYEE_ID2 = 100_013;
    public static final int MANAGER_ID1 = 100_009;
    public static final int MANAGER_ID2 = 100_014;

    public static final Task task1 = new Task(TASK_ID, "Литохимия для квартального отчета по Узюпскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 30, 10, 0), "Необходимо подготовить разноску по участкам Троицкий и Успенский", false, false);
    public static final Task task2 = new Task(PERFORMED_TASK_ID, "Глава \"Бурение\" в проект по Кизасскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 15, 12, 0), "Составить ГТН, написать текстовую часть", true, false);
    public static final Task task3 = new Task(TASK_ID + 2, "Заявки на ТМЦ на полевой сезон", LocalDateTime.of(2021, Month.DECEMBER, 20, 15, 0), "Сформировать заявки на покупку ТМЦ для Кизасского и Узюпского объектов, утвердить в отделе МТС", false, false);
    public static final Task task4 = new Task(TASK_ID + 3, "Правки по геологической карте 25-го масштаба", LocalDateTime.of(2021, Month.DECEMBER, 25, 16, 0), "Исправить: условные обозначения; линии разломов; привести к масштабу", false, false);
    public static final Task task5 = new Task(TASK_ID + 4, "Внешний контроль литохимических проб", LocalDateTime.of(2021, Month.DECEMBER, 20, 17, 0), "Отобрать и отправить на внешний контроль 350 проб по участку Семеновскому", false, false);

    public static final List<Task> departmentTasks = List.of(task2, task3, task1);

    public static Task getNew() {
        return new Task(null, "Заголовок", LocalDateTime.of(2021, Month.DECEMBER, 20, 10, 0), "Описание задачи", false, false);
    }

    public static Task getUpdated() {
        return new Task(TASK_ID, "Литохимия для квартального отчета по Узюпскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 20, 10, 0), "Необходимо подготовить разноску по участкам Троицкий и Успенский",false, false);
    }

    public static Task getPerformed() {
        return new Task(TASK_ID, "Литохимия для квартального отчета по Узюпскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 30, 10, 0), "Необходимо подготовить разноску по участкам Троицкий и Успенский", true, false);
    }

    public static Task getAccepted() {
        return new Task(PERFORMED_TASK_ID, "Глава \"Бурение\" в проект по Кизасскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 15, 12, 0), "Составить ГТН, написать текстовую часть", true, true);
    }

    public static Task getDeclined() {
        return new Task(PERFORMED_TASK_ID, "Глава \"Бурение\" в проект по Кизасскому объекту", LocalDateTime.of(2021, Month.DECEMBER, 15, 12, 0), "Составить ГТН, написать текстовую часть", false, false);
    }
}
