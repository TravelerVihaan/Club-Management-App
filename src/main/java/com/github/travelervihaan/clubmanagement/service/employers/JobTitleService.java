package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.JobTitle;
import com.github.travelervihaan.clubmanagement.repository.employers.JobTitleRepository;
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

    public void addNewJobTitle(@Valid JobTitle jobTitle, BindingResult result) {
        if (!result.hasErrors()) {
            if (jobTitleRepository.findByJobTitle(jobTitle.getJobTitle()).isEmpty())
                jobTitleRepository.save(jobTitle);
        }
    }
}
