package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final String NULL = "NULL";
    private final List<Employee> employeeList;
    private final Map<Integer, List<Employee>> projectsMap;


    public EmployeeServiceImpl() {
        employeeList = new ArrayList<>();
        projectsMap = new HashMap<>();
    }

    @Override
    public void handleLine(String line) throws Exception {
        String[] numbers = line.replaceAll("\\s", "").split(",");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        int projectId = 0;
        int employeeId = 0;
        Date dateFrom = null;
        Date dateTo;

        if (numbers[0] != null) {
            employeeId = Integer.parseInt(numbers[0]);
        }

        if (numbers[1] != null) {
            projectId = Integer.parseInt(numbers[1]);
        }

        if (numbers[2] != null) {
            dateFrom = format.parse(numbers[2]);
        }

        if (numbers[3] != null && !numbers[3].equals(NULL)) {
            dateTo = format.parse(numbers[3]);
        } else {
            dateTo = new Date();
            format.format(dateTo);
        }

        if (employeeId != 0 && projectId != 0 && dateFrom != null && dateTo != null) {
            Employee employee = new Employee(employeeId, projectId, dateFrom, dateTo);
            employeeList.add(employee);

            if (!projectsMap.containsKey(projectId)) {
                projectsMap.put(projectId, new ArrayList<>());
            }
            projectsMap.get(projectId).add(employee);
        } else throw new Exception();
    }


    public Map<Integer, Integer> getMostWorkedProject() {
        Map<Integer, Integer> mapHours = new HashMap<>();

        for (Employee employee : employeeList) {
            List<Employee> employees = projectsMap.get(employee.getProjectId());

            int sumDays = 0;
            for (Employee value : employees) {
                sumDays += value.getDays();
            }
            mapHours.put(employee.getProjectId(), sumDays);

        }

        Map<Integer, Integer> sortedProjects = new HashMap<>();

        mapHours.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedProjects.put(x.getKey(), x.getValue()));

        return sortedProjects;
    }

    public List<Map<String, Integer>> getResult() {
        List<Map<String, Integer>> result = new ArrayList<>();
        Map<Integer, Integer> sortedHours;
        sortedHours = getMostWorkedProject();

        for (Map.Entry<Integer, Integer> entry : sortedHours.entrySet()) {
            Map<String, Integer> projectMap = new HashMap<>();
            List<Employee> projectEmployees;
            projectEmployees = projectsMap.get(entry.getKey());

            projectMap.put("project_ID", entry.getKey());
            projectMap.put("days", entry.getValue());

            for (int i = 0; i < projectEmployees.size(); i++) {
                String key = "employee_" + (i + 1);
                projectMap.put(key, projectEmployees.get(i).getEmployeeId());
            }
            result.add(projectMap);
        }
        return result;


    }


}









