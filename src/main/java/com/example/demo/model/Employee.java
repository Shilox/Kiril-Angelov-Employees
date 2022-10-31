package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    private Integer employeeId;
    private Integer projectId;
    private Date dateFrom;
    private Date dateTo;
    private final long days;


    public Employee(Integer employeeId, Integer projectId, Date dateFrom, Date dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        long duration = dateTo.getTime() - dateFrom.getTime();

        days = TimeUnit.MILLISECONDS.toDays(duration);
    }

    public long getDays(){ return days; }

    }



