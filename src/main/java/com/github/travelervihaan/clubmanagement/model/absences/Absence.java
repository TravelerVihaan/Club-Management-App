package com.github.travelervihaan.clubmanagement.model.absences;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="absences")
public class Absence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence")
    private Long id;
    @NotNull
    @Column(nullable = false)
    private LocalDate absenceFromDay;
    @NotNull
    @Column(nullable = false)
    private LocalDate absenceToDay;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="approval_id")
    private AbsenceApprovalStatus absenceApprovalStatus;
    @ManyToOne
    @JoinColumn(name="absence_type_id")
    private AbsenceType absenceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getAbsenceFromDay() {
        return absenceFromDay;
    }

    public void setAbsenceFromDay(LocalDate absenceFromDay) {
        this.absenceFromDay = absenceFromDay;
    }

    public LocalDate getAbsenceToDay() {
        return absenceToDay;
    }

    public void setAbsenceToDay(LocalDate absenceToDay) {
        this.absenceToDay = absenceToDay;
    }

    public AbsenceApprovalStatus getAbsenceApprovalStatus() {
        return absenceApprovalStatus;
    }

    public void setAbsenceApprovalStatus(AbsenceApprovalStatus absenceApprovalStatus) {
        this.absenceApprovalStatus = absenceApprovalStatus;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", absenceFromDay=" + absenceFromDay +
                ", absenceToDay=" + absenceToDay +
                ", employee=" + employee +
                ", absenceApprovalStatus=" + absenceApprovalStatus +
                ", absenceType=" + absenceType +
                '}';
    }
}
