package com.github.travelervihaan.clubmanagement.repository.payrolls;

import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Query("SELECT p, e.name, e.surname FROM payrolls p INNER JOIN employers e ON p.employee_id = e.id_employee AND e.username =:username")
    List<Payroll> findAllPayrollsOfEmployee(@Param("username") String username);
}
