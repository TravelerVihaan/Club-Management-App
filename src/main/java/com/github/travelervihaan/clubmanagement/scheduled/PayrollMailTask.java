package com.github.travelervihaan.clubmanagement.scheduled;

import com.github.travelervihaan.clubmanagement.service.mails.PayrollMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PayrollMailTask {

    private PayrollMailService payrollMailService;

    @Autowired
    public PayrollMailTask(PayrollMailService payrollMailService){
        this.payrollMailService = payrollMailService;
    }

    @Scheduled(cron = "0 0 10 1 * ?")
    public void preparePayrolls(){
        payrollMailService.generatePayrolls();
    }
}
