package models;

import java.time.LocalDateTime;

public class Schedule {
    private LocalDateTime beginWorkingHours;
    private LocalDateTime endWorkingHours;




    public Schedule(LocalDateTime beginWorkingHours, LocalDateTime endWorkingHours) {
        this.beginWorkingHours = beginWorkingHours;
        this.endWorkingHours = endWorkingHours;
    }



    public boolean checkOverlapping(Schedule schedule) {
        return this.beginWorkingHours.isBefore(schedule.endWorkingHours) && schedule.beginWorkingHours.isBefore(this.endWorkingHours);
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "beginWorkingHours=" + beginWorkingHours +
                ", endWorkingHours=" + endWorkingHours +
                '}';
    }
}
