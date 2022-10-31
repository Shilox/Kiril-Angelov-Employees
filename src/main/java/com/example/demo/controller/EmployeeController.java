package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Controller
public  class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/readFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public List<Map<String, Integer>> readFile(@RequestPart("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(line -> {
                        try {
                            employeeService.handleLine(line);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }


        return employeeService.getResult();
    }
}



