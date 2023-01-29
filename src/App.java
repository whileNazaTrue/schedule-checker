import exceptions.EmployeeNotFoundException;
import exceptions.InvalidWeekdayException;
import models.Employee;
import utils.EmployeeScheduleIO;
import models.Schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {

    EmployeeScheduleIO employeeScheduleIO = new EmployeeScheduleIO();
    List<Employee> employees;

    public App() {
        this.employees = new ArrayList<>();
    }




    private Employee getEmployeeByName(String employeeName) throws EmployeeNotFoundException {
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public String showEmployeeData(String employeeName) throws EmployeeNotFoundException {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                sb.append(employee.getName());
                for (Schedule schedule : employee.getSchedules()) {
                    appendSchedules(sb, formatter, schedule);
                }
            }
        }
        if (sb.length() == 0) {
            throw new EmployeeNotFoundException();
        }
        return sb.toString();
    }


    public String getAllEmployeesString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Employee employee : employees) {
            sb.append(employee.getName());
            for (Schedule schedule : employee.getSchedules()) {
                {
                    appendSchedules(sb, formatter, schedule);
                }
            }
        }
        return sb.toString();
    }

    private static void appendSchedules(StringBuilder sb, DateTimeFormatter formatter, Schedule schedule) {
        String beginHour = schedule.getBeginWorkingHours().format(formatter);
        String endHour = schedule.getEndWorkingHours().format(formatter);
        sb.append(" Begins at: ").append(beginHour).append(" Ends at: ").append(endHour);
        sb.append("\n");
    }


    public void loadSchedulesFromFile() throws IOException, InvalidWeekdayException {
        List<Employee> employeeList = this.employeeScheduleIO.loadScheduleFromFile();
        this.employees.addAll(employeeList);
    }

    public Map<String, Integer> findMatchingSchedules() {
        Map<String, Integer> matchingSchedules = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee2 = employees.get(j);
                matchSchedules(employee1, employee2, matchingSchedules);
            }
        }
        return matchingSchedules;
    }

    private void matchSchedules(Employee employee1, Employee employee2, Map<String, Integer> matchingSchedules) {
        for (Schedule schedule1 : employee1.getSchedules()) {
            for (Schedule schedule2 : employee2.getSchedules()) {
                if (schedule1.compareSchedules(schedule2)) {
                    String key = employee1.getName() + "-" + employee2.getName();
                    matchingSchedules.put(key, matchingSchedules.getOrDefault(key, 0) + 1);
                }
            }
        }
    }

    public void saveMatchingSchedulesToFile(Map<String, Integer> matchingSchedules) throws IOException {
        String curdir = new File(".").getCanonicalPath();
        String filename = curdir + "\\data\\output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            if (matchingSchedules.isEmpty()) {
                writer.write("No matching schedules found");
            }else {
                for (Map.Entry<String, Integer> entry : matchingSchedules.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
            }
        }
    }



}
