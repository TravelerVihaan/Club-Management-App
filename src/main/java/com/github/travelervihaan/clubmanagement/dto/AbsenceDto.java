package com.github.travelervihaan.clubmanagement.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AbsenceDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate absenceFromDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate absenceToDay;
    private String absenceType;
    private String username;

    public LocalDate getAbsenceFromDay() {
        return absenceFromDay;
    }

    public void setAbsenceFromDay(LocalDate absenceFromDay) {
        this.absenceFromDay = absenceFromDay;
    }

    public LocalDate getAbsenceToDay() {
        return absenceToDay;
    }

    public void setAbsenceToDay(LocalDate absenceToDay) {
        this.absenceToDay = absenceToDay;
    }

    public String getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(String absenceType) {
        this.absenceType = absenceType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
