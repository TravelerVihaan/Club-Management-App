package com.github.travelervihaan.clubmanagement.model.employers;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employers")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employee")
	private Long id;
	@NotEmpty
	@Column(unique=true, nullable = false)
	private String username;
	@NotEmpty
	@Size(min = 8)
	@Column(nullable = false)
	private String password;
	@NotEmpty
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Column(nullable = false)
	private String surname;
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_details_id")
	private EmployeeDetails employeeDetails;

	@ManyToMany(mappedBy="employers")
	private List<WorkDay> workDays;
	@OneToMany(mappedBy = "employee")
	private List<Absence> absences;
	@OneToMany(mappedBy = "employee")
	private List<Payroll> payrolls;


	public Employee(){}
	public Employee(@NotEmpty String username, @NotEmpty String password, @NotEmpty String name, @NotEmpty String surname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public List<WorkDay> getWorkDays() {
		return workDays;
	}

	public void setWorkDays(List<WorkDay> workDays) {
		this.workDays = workDays;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public List<Payroll> getPayrolls() {
		return payrolls;
	}

	public void setPayrolls(List<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", employeeDetails=" + employeeDetails +
				'}';
	}
}
