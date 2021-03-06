package com.github.travelervihaan.clubmanagement.model.absences;

import com.github.travelervihaan.clubmanagement.model.employees.Employee;

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
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "absence_from_day", nullable = false)
    private LocalDate absenceFromDay;
    @NotNull
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "absence_to_day", nullable = false)
    private LocalDate absenceToDay;

    @NotNull
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="approval_id")
    private AbsenceApprovalStatus absenceApprovalStatus;
    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="absence_type_id")
    private AbsenceType absenceType;

    public Absence(){}
    public Absence(@NotNull LocalDate absenceFromDay, @NotNull LocalDate absenceToDay, @NotNull Employee employee, @NotNull AbsenceApprovalStatus absenceApprovalStatus, @NotNull AbsenceType absenceType) {
        this.absenceFromDay = absenceFromDay;
        this.absenceToDay = absenceToDay;
        this.employee = employee;
        this.absenceApprovalStatus = absenceApprovalStatus;
        this.absenceType = absenceType;
    }

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
