package models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private List<Schedule> schedules;



    public Employee(String name) {
        this.name = name;
        this.schedules = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public List<Schedule> getSchedules() {
        return schedules;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", schedules=" + schedules +
                '}';
    }
}
