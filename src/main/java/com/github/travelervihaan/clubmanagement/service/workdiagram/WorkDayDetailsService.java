package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayRepository;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkDayDetailsService {

    private WorkDayRepository workDayRepository;
    private EmployeeService employeeService;

    @Autowired
    public WorkDayDetailsService(WorkDayRepository workDayRepository, EmployeeService employeeService){
        this.workDayRepository = workDayRepository;
        this.employeeService = employeeService;
    }

    void saveImportanceLevel(WorkDay workDay, WorkDayImportance workDayImportance){
        workDay.setWorkDayImportance(workDayImportance);
        while(workDay.getEmployers().size() > workDayImportance.getImportanceLevel()){
            workDay.getEmployers().remove(workDay.getEmployers().size()-1);
        }
        workDay.setId(workDay.getId());
        workDayRepository.save(workDay);
    }

    void addEmployeeToWorkDay(WorkDay workDay, String employeeUsername){
        if(workDay.getEmployers().stream().noneMatch(employee -> employee.getUsername().equalsIgnoreCase(employeeUsername))) {
            workDay.getEmployers().add(employeeService.getEmployeeByUsername(employeeUsername).orElseThrow());
            workDayRepository.save(workDay);
        }
    }

    void deleteEmployeeToWorkDay(WorkDay workDay, String employeeUsername){
        if(workDay.getEmployers().stream().anyMatch(employee -> employee.getUsername().equalsIgnoreCase(employeeUsername))) {
            workDay.getEmployers().remove(employeeService.getEmployeeByUsername(employeeUsername).orElseThrow());
            workDayRepository.save(workDay);
        }
    }

    void saveBookedArtist(WorkDay workDay, String artist){
        if(artist != null && !artist.equals("")) {
            workDay.setBookedArtist(artist);
            workDay.setId(workDay.getId());
            workDayRepository.save(workDay);
        }
    }

    void saveWorkTime(WorkDay workDay, int workTime){
        if(workTime > 0 && workTime < 24){
            workDay.setWorkingTime(workTime);
            workDay.setId(workDay.getId());
            workDayRepository.save(workDay);
        }
    }
}
