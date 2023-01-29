package exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException() {
        super("Exception: Employee not found");
    }

}
