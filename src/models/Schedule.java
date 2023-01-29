package models;

import java.time.LocalDateTime;

public class Schedule {
    LocalDateTime beginWorkingHours;
    LocalDateTime endWorkingHours;




    public Schedule(LocalDateTime beginWorkingHours, LocalDateTime endWorkingHours) {
        this.beginWorkingHours = beginWorkingHours;
        this.endWorkingHours = endWorkingHours;
    }


    public LocalDateTime getBeginWorkingHours() {
        return beginWorkingHours;
    }

    public LocalDateTime getEndWorkingHours() {
        return endWorkingHours;
    }

    public boolean compareSchedules(Schedule schedule) {
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
