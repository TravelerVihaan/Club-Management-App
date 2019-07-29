package com.github.travelervihaan.clubmanagement.scheduled;

import com.github.travelervihaan.clubmanagement.service.mails.TerminatedContractMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContractTerminateTask {

    private TerminatedContractMailService terminatedContractMailService;

    @Autowired
    public ContractTerminateTask(TerminatedContractMailService terminatedContractMailService){
        this.terminatedContractMailService = terminatedContractMailService;
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void checkTerminateContracts(){
        terminatedContractMailService.sendMailsWithTerminatedContractInfo();
    }
}
