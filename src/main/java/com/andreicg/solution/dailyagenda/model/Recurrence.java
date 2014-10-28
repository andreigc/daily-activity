package com.andreicg.solution.dailyagenda.model;

import java.util.Date;

public class Recurrence {

    private int id;
    private int recurrenceType;
    private int recurrenceDayOfTheWeek;
    private Date startDate;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getRecurrenceType() {
	return recurrenceType;
    }

    public void setRecurrenceType(int recurrenceType) {
	this.recurrenceType = recurrenceType;
    }

    public int getRecurrenceDayOfTheWeek() {
	return recurrenceDayOfTheWeek;
    }

    public void setRecurrenceDayOfTheWeek(int recurrenceDayOfTheWeek) {
	this.recurrenceDayOfTheWeek = recurrenceDayOfTheWeek;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

}
