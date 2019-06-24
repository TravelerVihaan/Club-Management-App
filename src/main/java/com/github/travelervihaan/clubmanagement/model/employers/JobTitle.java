package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "job_title")
public class JobTitle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_title")
    private Long id;
    @NotEmpty
    @Column(name="title", nullable = false, unique = true)
    private String jobTitle;

    @OneToMany(mappedBy = "jobTitle")
    private List<EmployeeDetails> employeeDetails;

    public JobTitle(){}

    public JobTitle(@NotEmpty String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    @Override
    public String toString() {
        return "JobTitle{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
