package com.github.travelervihaan.clubmanagement.service.mails;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.service.payrolls.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollMailService {

    private JavaMailSender mailSender;
    private PayrollService payrollService;
    private final String MAIL_FROM = "club@club.com";
    private final String MAIL_SUBJECT = "[CLUB - WYPLATA]";
    private final String MAIL_TEXT = "Witaj, na Twoim koncie dostepna jest nowa informacja o wypłacie za ubiegły miesiąc." +
                                        " Zapoznaj się z nią. \n Pozdrawiamy!";

    @Autowired
    public PayrollMailService(JavaMailSender mailSender, PayrollService payrollService){
        this.mailSender = mailSender;
        this.payrollService = payrollService;
    }

    public void sendPayrollsMails(List<Employee> employeeList){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(setRecipientList(employeeList));
        mailMessage.setFrom(MAIL_SUBJECT);
        mailMessage.setText(MAIL_TEXT);
        mailSender.send(mailMessage);

    }

    private String[] setRecipientList(List<Employee> employeeList){
        List<String> mailList = new ArrayList<>();
        employeeList.forEach(e -> mailList.add(e.getEmail()));
        String[] recipientList = new String[mailList.size()];
        return mailList.toArray(recipientList);
    }

}
