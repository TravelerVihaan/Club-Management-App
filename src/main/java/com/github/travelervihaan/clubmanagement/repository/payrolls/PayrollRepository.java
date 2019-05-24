package com.github.travelervihaan.clubmanagement.repository.payrolls;

import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
}
