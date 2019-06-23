package com.github.travelervihaan.clubmanagement.model.absences;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="absences_approvals")
public class AbsenceApprovalStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence_approval")
    private Long id;
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany(mappedBy = "absenceApprovalStatus")
    private List<Absence> absences;

    public AbsenceApprovalStatus(){}

    public AbsenceApprovalStatus(@NotEmpty String status) {
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
