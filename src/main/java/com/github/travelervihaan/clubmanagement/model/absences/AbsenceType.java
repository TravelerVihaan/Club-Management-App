package com.github.travelervihaan.clubmanagement.model.absences;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="absence_types")
public class AbsenceType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence")
    private Long id;

    @NotEmpty
    @Column(name = "absence_type", unique = true, nullable = false)
    private String absenceType;

    @OneToMany(mappedBy = "absenceType")
    private List<Absence> absences;

    public AbsenceType(){}

    public AbsenceType(@NotEmpty String absenceType) {
        this.absenceType = absenceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(String absenceType) {
        this.absenceType = absenceType;
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
                ", abscenceType='" + absenceType + '\'' +
                '}';
    }
}
