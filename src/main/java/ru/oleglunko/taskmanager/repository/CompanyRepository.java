package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByEmployeesId(int employeeId);

    @Modifying
    @Query("DELETE FROM Company c WHERE c.id = ?1")
    int delete(int id);
}
