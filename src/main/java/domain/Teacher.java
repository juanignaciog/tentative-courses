package domain;

import domain.course.Course;
import domain.schedule.Schedule;

import java.util.List;
import java.util.stream.Collectors;

public class Teacher implements WithSchedule {

    private List<Schedule> schedules;
    @Override
    public List getAvailableSchedule() {
        return this.schedules;
    }

    @Override
    public void setSchedules(List<Schedule> scheduleList) {
        this.schedules = scheduleList;
    }

    public void addSchedule(Schedule schedule){
        if(!this.schedules.contains(schedule)){
            this.schedules.add(schedule);
        }
    }

    public void assignCourse(Course course) {
        this.schedules = schedules.stream().filter(aSchedule -> !aSchedule.equals(course.getSchedule())).collect(Collectors.toList());
    }
    
    public Teacher(List<Schedule> schedules){
        this.schedules = schedules;
    }
    
    public Teacher(){}
    
    public boolean canWorkIn(List<Schedule> posibleSchedules) {
        return posibleSchedules.stream().anyMatch(aSchedule -> this.schedules.contains(aSchedule));
    }
}
