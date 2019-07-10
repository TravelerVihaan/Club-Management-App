package com.github.travelervihaan.clubmanagement.service.mails;

import com.github.travelervihaan.clubmanagement.service.payrolls.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PayrollMailService {

    private JavaMailSender mailSender;
    private PayrollService payrollService;

    @Autowired
    public PayrollMailService(JavaMailSender mailSender, PayrollService payrollService){
        this.mailSender = mailSender;
        this.payrollService = payrollService;
    }

    public void generatePayrolls(){

    }
}
