package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.JobTitle;
import com.github.travelervihaan.clubmanagement.repository.employers.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleService {

    private JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleService(JobTitleRepository jobTitleRepository){
        this.jobTitleRepository = jobTitleRepository;
    }

    List<JobTitle> getAllJobTitles(){
        return jobTitleRepository.findAll();
    }
}
