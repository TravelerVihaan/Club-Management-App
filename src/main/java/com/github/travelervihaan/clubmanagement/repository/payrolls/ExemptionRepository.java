package com.github.travelervihaan.clubmanagement.repository.payrolls;

import com.github.travelervihaan.clubmanagement.model.payrolls.Exemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemptionRepository extends JpaRepository<Exemption, Long> {
}
