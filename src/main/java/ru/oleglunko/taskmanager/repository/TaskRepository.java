package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
