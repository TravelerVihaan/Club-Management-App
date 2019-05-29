package com.github.travelervihaan.clubmanagement.model.absences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="abscences")
public class AbsenceType implements Serializable {

    private static final long serialVersionUID = 13L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence")
    private Long id;

    private String abscenceType;

    @OneToMany(mappedBy = "absenceApproval")
    private List<Absence> absences;

    public AbsenceType(){}
    public AbsenceType(String abscenceType) {
        this.abscenceType = abscenceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbscenceType() {
        return abscenceType;
    }

    public void setAbscenceType(String abscenceType) {
        this.abscenceType = abscenceType;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    @Override
    public String toString() {
        return "AbsenceType{" +
                "id=" + id +
                ", abscenceType='" + abscenceType + '\'' +
                '}';
    }
}
