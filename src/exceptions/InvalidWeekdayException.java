package exceptions;

public class InvalidWeekdayException extends Exception {
    public InvalidWeekdayException() {
        super("Exception: Invalid weekday. Input should be MO,TU,WE,TH,FR,SA or SU");
    }
}

