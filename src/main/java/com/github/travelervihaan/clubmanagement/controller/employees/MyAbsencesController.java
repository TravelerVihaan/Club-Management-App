package com.github.travelervihaan.clubmanagement.controller.employees;

import com.github.travelervihaan.clubmanagement.dto.AbsenceDto;
import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.model.absences.AbsenceType;
import com.github.travelervihaan.clubmanagement.service.absences.AbsenceTypeService;
import com.github.travelervihaan.clubmanagement.service.absences.NewAbsenceService;
import com.github.travelervihaan.clubmanagement.service.mappers.AbsenceDtoMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyAbsencesController {

    private NewAbsenceService newAbsenceService;
    private AbsenceTypeService absenceTypeService;
    private AbsenceDtoMapper absenceDtoMapper;

    public MyAbsencesController(NewAbsenceService newAbsenceService, AbsenceTypeService absenceTypeService, AbsenceDtoMapper absenceDtoMapper){
        this.newAbsenceService = newAbsenceService;
        this.absenceTypeService = absenceTypeService;
        this.absenceDtoMapper = absenceDtoMapper;
    }

    @GetMapping("/myabsences")
    public String getAbsences(@RequestParam(defaultValue = "all") String filter, Model model){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        List<Absence> absences = newAbsenceService
                .getAbsencesOfEmployee(authentication.getName(), filter);
        model.addAttribute("absences", absences);
        model.addAttribute("newAbsence", new AbsenceDto());
        model.addAttribute("absenceTypes", absenceTypeService
                .getAllAbsenceTypes()
                .stream()
                .map(AbsenceType::getAbsenceType)
                .collect(Collectors.toList()));
        return "myabsences";
    }

    @PostMapping("/new-absence")
    public String sendNewAbsenceRequest(@ModelAttribute AbsenceDto absence){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        absence.setUsername(authentication.getName());
        newAbsenceService.addNewAbsence(absenceDtoMapper.convertFromDtoToEntity(absence));
        return "redirect:/myabsences";
    }
}
