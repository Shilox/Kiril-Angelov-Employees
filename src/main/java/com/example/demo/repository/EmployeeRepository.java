package com.example.demo.repository;


import java.text.ParseException;

public interface EmployeeRepository {


    void handleLine(String line) throws ParseException;
}
