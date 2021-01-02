package com.github.travelervihaan.clubmanagement.service.employees;

import com.github.travelervihaan.clubmanagement.model.employees.JobTitle;
import com.github.travelervihaan.clubmanagement.repository.employees.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
public class JobTitleService {

    private JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleService(JobTitleRepository jobTitleRepository){
        this.jobTitleRepository = jobTitleRepository;
    }

    public List<JobTitle> getAllJobTitles(){
        return jobTitleRepository.findAll();
    }

    public void deleteJobTitle(String jobTitle){
        if(jobTitleRepository.findByJobTitle(jobTitle).isPresent())
            jobTitleRepository.delete(jobTitleRepository.findByJobTitle(jobTitle).get());
    }

    public JobTitle getJobTitleByTitle(String title){
        return jobTitleRepository.findByJobTitle(title).orElseThrow();
    }

    public void addNewJobTitle(@Valid JobTitle jobTitle, BindingResult result) {
        if (!result.hasErrors()) {
            if (jobTitleRepository.findByJobTitle(jobTitle.getJobTitle()).isEmpty())
                jobTitleRepository.save(jobTitle);
        }
    }
}
