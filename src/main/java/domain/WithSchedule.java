package domain;

import domain.schedule.Schedule;

import java.util.List;

public interface WithSchedule {
    public List<Schedule> getAvailableSchedule();

    public void setSchedules(List<Schedule> scheduleList);
}
