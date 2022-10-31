package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.service.EmployeeServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeServiceImpl employeeService() {
        return new EmployeeServiceImpl();
    }
}