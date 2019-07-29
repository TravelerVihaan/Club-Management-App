package com.github.travelervihaan.clubmanagement.service.mails;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminatedContractMailService {

    private JavaMailSender mailSender;
    private EmployeeService employeeService;

    private final String MAIL_FROM = "club@club.com";
    private final String MAIL_SUBJECT = "[CLUB - KONTRAKT PRACOWNIKA WYGASŁ]";


    @Autowired
    public TerminatedContractMailService(JavaMailSender mailSender, EmployeeService employeeService){
        this.mailSender = mailSender;
        this.employeeService = employeeService;
    }

    public void sendMailsWithTerminatedContractInfo(){
        List<Employee> employers = employeeService.getEmployersWithTerminatedContract();
        employers
                .stream()
                .map(employee -> employee.getName()+" "+employee.getSurname())
                .forEach(this::prepareMailAboutTerminateContract);
    }

    private void prepareMailAboutTerminateContract(String employeeName){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(MAIL_SUBJECT);
        mailMessage.setFrom(MAIL_FROM);
        setRecipientList();
        mailMessage.setText("Witaj, "+
                " Pracownikowi "+ employeeName + "wygasła umowa.\n" +
                "Zaloguj się do aplikacji, aby przedłużyć ją, lub usunąć konto pracownika");
        mailSender.send(mailMessage);
    }

    private String[] setRecipientList(){
        List<Employee> managerList = employeeService.getEmployersByJobTitle("manager");
        List<String> mailList = new ArrayList<>();
        managerList.forEach(manager -> mailList.add(manager.getEmail()));
        String[] recipientList = new String[mailList.size()];
        mailList.toArray(recipientList);
        return recipientList;
    }
}
