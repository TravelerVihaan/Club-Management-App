package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.Comment;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.CommentRepository;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private WorkDayService workDayService;
    private EmployeeService employeeService;


    @Autowired
    public CommentService(CommentRepository commentRepository,WorkDayService workDayService,EmployeeService employeeService){
        this.commentRepository = commentRepository;
        this.workDayService = workDayService;
        this.employeeService = employeeService;
    }

    public void addNewComment(Long workDayId, String commentText, String username){
        Comment comment = new Comment();
        comment.setDate(LocalDateTime.now());
        comment.setEmployee(employeeService.getEmployeeByUsername(username).orElseThrow());
        comment.setText(commentText);
        comment.setWorkDay(workDayService.getWorkDayById(workDayId).orElseThrow());
        commentRepository.save(comment);
    }
}
