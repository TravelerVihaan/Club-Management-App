package com.github.travelervihaan.clubmanagement.model.absences;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="absences")
public class Absence implements Serializable {

    private static final long serialVersionUID = 11L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //FK
    private Employee employee;

    private int absenceFromDay;
    private int absenceFromMonth;

    private int absenceToDay;
    private int absenceToMonth;

    //FK
    private AbsenceApprovalStatus absenceApprovalStatus;
    //FK
    private AbsenceType absenceType;

}
