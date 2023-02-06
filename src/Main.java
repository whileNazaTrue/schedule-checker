import exceptions.EmployeeNotFoundException;
import exceptions.InvalidWeekdayException;
import models.Employee;
import utils.EmployeeScheduleIO;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws EmployeeNotFoundException, IOException, InvalidWeekdayException {
        EmployeeScheduleIO employeeScheduleIO = new EmployeeScheduleIO();
        App app = new App(employeeScheduleIO.loadScheduleFromFile());
        Map<String,Integer> matches = app.findMatchingSchedules();
        employeeScheduleIO.saveMatchingSchedulesToFile(matches);




    }
}