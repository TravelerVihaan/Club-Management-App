package com.github.travelervihaan.clubmanagement.model.absences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="absences_approvals")
public class AbsenceApprovalStatus implements Serializable {

    private static final long serialVersionUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence_approval")
    private Long id;

    private String status;

    @OneToMany(mappedBy = "absenceApproval")
    private List<Absence> absences;

    public AbsenceApprovalStatus(){}
    public AbsenceApprovalStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    @Override
    public String toString() {
        return "AbsenceApprovalStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}