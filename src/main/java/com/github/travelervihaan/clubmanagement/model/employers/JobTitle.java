package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "job_title")
public class JobTitle implements Serializable {

    private static final long serialVersionUID = 7021150458271420831L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_title")
    private Long id;
    @NotEmpty
    @Column(name="title")
    private String jobTitle;

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

    @Override
    public String toString() {
        return "JobTitle{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
