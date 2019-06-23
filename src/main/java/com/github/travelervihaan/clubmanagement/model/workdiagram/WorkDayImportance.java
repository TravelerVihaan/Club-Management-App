package com.github.travelervihaan.clubmanagement.model.workdiagram;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "work_day_importance")
public class WorkDayImportance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workday_importance")
    private Long id;

    @NotNull
    @Column(name = "importance_level", nullable = false, unique = true)
    private Integer importanceLevel;

    @OneToMany(mappedBy = "workDayImportance")
    private List<WorkDay> workDay;

    public WorkDayImportance(){}

    public WorkDayImportance(@NotNull Integer importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getImportanceLevel() { return importanceLevel; }

    public void setImportanceLevel(Integer importanceLevel) { this.importanceLevel = importanceLevel; }

    public List<WorkDay> getWorkDay() { return workDay; }

    public void setWorkDay(List<WorkDay> workDay) { this.workDay = workDay; }

    @Override
    public String toString() {
        return "WorkDayImportance{" +
                "id=" + id +
                ", importanceLevel=" + importanceLevel +
                '}';
    }
}
