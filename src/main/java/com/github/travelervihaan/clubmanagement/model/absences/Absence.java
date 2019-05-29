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

    private Integer absenceFromDay;
    private Integer absenceFromMonth;

    private Integer absenceToDay;
    private Integer absenceToMonth;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getAbsenceFromDay() {
        return absenceFromDay;
    }

    public void setAbsenceFromDay(Integer absenceFromDay) {
        this.absenceFromDay = absenceFromDay;
    }

    public Integer getAbsenceFromMonth() {
        return absenceFromMonth;
    }

    public void setAbsenceFromMonth(Integer absenceFromMonth) {
        this.absenceFromMonth = absenceFromMonth;
    }

    public Integer getAbsenceToDay() {
        return absenceToDay;
    }

    public void setAbsenceToDay(Integer absenceToDay) {
        this.absenceToDay = absenceToDay;
    }

    public Integer getAbsenceToMonth() {
        return absenceToMonth;
    }

    public void setAbsenceToMonth(Integer absenceToMonth) {
        this.absenceToMonth = absenceToMonth;
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
}
