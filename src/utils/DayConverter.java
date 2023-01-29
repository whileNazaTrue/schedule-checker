package utils;

public class DayConverter {
    public static int convertDay(String day) {
        return switch (day){
            case "MO" -> 1;
            case "TU" -> 2;
            case "WE" -> 3;
            case "TH" -> 4;
            case "FR" -> 5;
            case "SA" -> 6;
            case "SU" -> 7;
            default -> 0;
        };
    }


}
