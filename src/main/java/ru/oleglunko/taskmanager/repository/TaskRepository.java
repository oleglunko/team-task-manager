package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.author.id IN (SELECT e.id FROM Employee e LEFT JOIN Department d ON e.department.id = d.id WHERE d.id = ?1) ORDER BY t.deadline")
    List<Task> findAllInDepartment(int departmentId);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id = :id AND t.author.id = :employeeId")
    int delete(@Param("id") int id, @Param("employeeId") int employeeId);
}
