import exceptions.EmployeeNotFoundException;
import exceptions.InvalidWeekdayException;
import utils.EmployeeScheduleIO;

import java.awt.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws EmployeeNotFoundException, IOException, InvalidWeekdayException {
        App app = new App();


        try {
            app.loadSchedulesFromFile();
        } catch (InvalidWeekdayException e) {
            throw new InvalidWeekdayException();
        } catch (NoSuchFileException e) {
            System.out.println("ERROR: File not found");
        } catch (IOException e) {
            System.out.println("ERROR: Error reading file");
        }

        try {
            Map<String,Integer> map = app.findMatchingSchedules();
            app.saveMatchingSchedules(map);
        } catch (IOException e) {
            throw new IOException(e);
        }

    }
}