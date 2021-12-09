[![Codacy Badge](https://app.codacy.com/project/badge/Grade/5eb442c70ab94100a40e768adc3c868e)](https://www.codacy.com/gh/oleglunko/team-task-manager/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=oleglunko/team-task-manager&amp;utm_campaign=Badge_Grade)
# Team Task Manager (backend, in progress)

## Spring Security, Spring MVC, Spring Data JPA, Hibernate, SLF4J, REST(Jackson), Tomcat, PostgreSQL, Junit 5, AssertJ

### Описание проекта
Проект представляет собой систему управления рабочими задачами с элементами справочника по организации. 
Администратор может редактировать профиль компании, создавать/изменять/удалять структурные подразделения и профили 
сотрудников. Начальник структурного подразделения может создавать/изменять/удалять задачи, а также выполнять контроль 
(принять или отправить на доработку). Сотрудники могут просматривать задачи, созданные в пределах своего структурного 
подразделения, а также получать список выполняемых им задач. По REST-интерфейсу доступна базовая аутентификация, доступ 
к ресурсам на основе ролей.

<details>
<summary>TODO</summary>

- Тестирование REST-интерфейса
- Обработка ошибок
- Расширение функционала (доработка управления задачами, профилями сотрудников, фильтрация, поиск)
- UI
- Оптимизация приложения
- Документация
</details>

