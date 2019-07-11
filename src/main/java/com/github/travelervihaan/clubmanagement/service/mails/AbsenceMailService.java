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
public class AbsenceMailService {

    private JavaMailSender mailSender;
    private EmployeeService employeeService;

    private final String MAIL_FROM = "club@club.com";
    private final String MAIL_SUBJECT = "[CLUB - NIEOBECNOSC PRACOWNIKA]";

    @Autowired
    public AbsenceMailService(JavaMailSender mailSender, EmployeeService employeeService){
        this.mailSender = mailSender;
        this.employeeService = employeeService;
    }

    public void sendMailInformationAboutAbsence(Employee employee){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(MAIL_SUBJECT);
        mailMessage.setFrom(MAIL_FROM);
        mailMessage.setTo(setRecipientList());
        mailMessage.setText("Witaj, "+employee.getName()+" "+employee.getSurname()
                +" zglosil nieobecnosc. \n" +
                "Zaloguj się do aplikacji, aby sprawdzić czego ona dotyczy i zatwierdzić ją");
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
