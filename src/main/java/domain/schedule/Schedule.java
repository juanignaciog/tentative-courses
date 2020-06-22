package domain.schedule;

import java.time.LocalTime;

public class Schedule {
    private LocalTime time;
    private DAY day;

    public enum DAY {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY
    }

    public LocalTime getTime(){
        return this.time;
    }

    public DAY getDay(){
        return this.day;
    }

    public Schedule(DAY day, LocalTime time){
        this.day = day;
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        
        if(!(obj instanceof Schedule)){
            return false;
        }
        Schedule comparedSchedule = (Schedule) obj;
        return comparedSchedule.getTime().equals(this.time) && comparedSchedule.getDay().equals(this.day);
    }
}
