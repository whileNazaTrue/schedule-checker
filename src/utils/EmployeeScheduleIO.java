package utils;

import exceptions.InvalidWeekdayException;
import models.Employee;
import models.Schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static utils.DayConverter.convertDay;

public class EmployeeScheduleIO {

    public List<Employee> loadScheduleFromFile() throws IOException, InvalidWeekdayException {
        createDataDirectory();
        String content = readFileContent();
        String[] lines = splitByLineBreak(content);
        return processAllEmployees(lines);
    }

    private void createDataDirectory() {
        new File("./data").mkdirs();
    }

    private String readFileContent() throws IOException {
        String curdir = new File(".").getCanonicalPath();
        Path path = Path.of(curdir + "\\data\\input.txt");
        return Files.readString(path);
    }

    private String[] splitByLineBreak(String content) {
        return content.split("\\r?\\n");
    }

    private List<Employee> processAllEmployees(String[] lines) throws InvalidWeekdayException {
        List<Employee> employees = new ArrayList<>();
        for (String line : lines){
            Employee e = processEmployee(line);
            employees.add(e);
        }
        return employees;
    }

    private Employee processEmployee(String line) throws InvalidWeekdayException {
        String[] tokens = line.split("=");
        String employeeName = tokens[0];
        Employee e = new Employee(employeeName);
        String[] schedules = tokens[1].split(",");
        for (String schedule : schedules){
            Schedule s = processSchedule(schedule);
            e.getSchedules().add(s);
        }
        return e;
    }

    private Schedule processSchedule(String schedule) throws InvalidWeekdayException {
        int weekDay = convertDay(schedule.substring(0, 2));
        if (weekDay == 0) {
            throw new InvalidWeekdayException();
        }
        String beginWorkingHours = schedule.substring(2, 7);
        String endWorkingHours = schedule.substring(8, 13);
        LocalDateTime begin = LocalDateTime.now().with(nextOrSame(DayOfWeek.of(weekDay))).withHour(Integer.parseInt(beginWorkingHours.substring(0, 2))).withMinute(Integer.parseInt(beginWorkingHours.substring(3, 5)));
        LocalDateTime end = LocalDateTime.now().with(nextOrSame(DayOfWeek.of(weekDay))).withHour(Integer.parseInt(endWorkingHours.substring(0, 2))).withMinute(Integer.parseInt(endWorkingHours.substring(3, 5)));
        return new Schedule(begin, end);
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
