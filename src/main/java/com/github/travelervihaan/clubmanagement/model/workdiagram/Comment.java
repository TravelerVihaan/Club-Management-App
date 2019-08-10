package com.github.travelervihaan.clubmanagement.model.workdiagram;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "hh:mm dd-MM-yyyy")
    private LocalDateTime date;

    @NotEmpty
    @Column(nullable = false)
    private String text;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="workday_id")
    private WorkDay workDay;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Comment(){}
    public Comment(@NotNull LocalDateTime date, @NotEmpty String text) {
        this.date = date;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", workDay=" + workDay +
                ", employee=" + employee +
                '}';
    }
}
