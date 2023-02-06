import models.Employee;
import models.Schedule;

import java.util.*;

public class App {

    private final List<Employee> employees;

    public App() {
        this.employees = new ArrayList<>();
    }

    public App(List<Employee> employees) {
        this.employees = employees;
    }


    private void matchSchedules(Employee employee1, Employee employee2, Map<String, Integer> matchingSchedules) {
        for (Schedule schedule1 : employee1.getSchedules()) {
            for (Schedule schedule2 : employee2.getSchedules()) {
                if (schedule1.checkOverlapping(schedule2)) {
                    String key = employee1.getName() + "-" + employee2.getName();
                    matchingSchedules.put(key, matchingSchedules.getOrDefault(key, 0) + 1);
                }
            }
        }
    }
    public Map<String, Integer> findMatchingSchedules(){
        Map<String, Integer> matchingSchedules = new HashMap<>();
        int employeeSize = employees.size();
        for (int i = 0; i < employeeSize; i++) {
            Employee employee1 = employees.get(i);
            for (int j = i + 1; j < employeeSize; j++) {
                Employee employee2 = employees.get(j);
                matchSchedules(employee1, employee2, matchingSchedules);
            }
        }
        return matchingSchedules;
    }








}
