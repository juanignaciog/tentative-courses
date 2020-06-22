package domain.course;

import domain.Level;
import domain.schedule.Schedule;
import domain.Student;
import domain.Teacher;
import domain.schedule.Scheduler;

import java.util.*;

public abstract class Course {
    protected Teacher teacher;
    protected List<Student> enrolled;
    protected Schedule schedule;

    public abstract Level getLevel();

    public Teacher getTeacher() {
        return teacher;
    }

    public Schedule getSchedule() {
        return schedule;
    }
    
    public List<Student> getEnrolled(){
        return enrolled;
    }

    public void assignTeacher(List<Student> studentsSplitInGroups, List<Teacher> availableTeachers) {
        List<List<Schedule>> schedulesOfEveryStudent = new ArrayList<>();
        for(Student student:studentsSplitInGroups){
            schedulesOfEveryStudent.add(student.getAvailableSchedule());
        }
        List<Schedule> posibleSchedules = Scheduler.findCommonSchedule(schedulesOfEveryStudent);
        Teacher selectedTeacher = availableTeachers.stream().filter(aTeacher -> aTeacher.canWorkIn(posibleSchedules)).findFirst().orElse(null);
    
        if(selectedTeacher != null){
            List<List<Schedule>> schedules = Arrays.asList(posibleSchedules, selectedTeacher.getAvailableSchedule());
            Schedule selectedSchedule = Scheduler.findCommonSchedule(schedules).get(0);
            this.schedule = selectedSchedule;
            this.teacher = selectedTeacher;
            this.enrolled = studentsSplitInGroups;
            studentsSplitInGroups.forEach(aStudent -> aStudent.assignCourse(this));
            selectedTeacher.assignCourse(this);
        }
    }
    
}
