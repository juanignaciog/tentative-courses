package domain;

import domain.course.Course;
import domain.modality.Modality;
import domain.schedule.Schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student implements WithSchedule {

    private String name;    //solo para identificar mejor
    private Modality modality;
    private Level level;
    private List<Schedule> schedules;

    @Override
    public List getAvailableSchedule() {
        return this.schedules;
    }

    @Override
    public void setSchedules(List<Schedule> scheduleList) {
        this.schedules = scheduleList;
    }

    public String getName() {
        return name;
    }

    public Student(String name, Modality modality, List<Schedule> schedules, Level level) {
        this.name = name;
        this.modality = modality;
        this.schedules = schedules;
        this.level = level;
    }
    
    
    public Level getLevel(){
        return level;
    }

    public static List<List<Student>> formGroups(List<Student> totalStudents){
        List<List<Student>> totalGroups = new ArrayList<>();
        List<Student> groupFormed = new ArrayList<>();
        for(Student individualStudent : totalStudents){
            if(individualStudent.canAddToGroup(groupFormed)){
                groupFormed.add(individualStudent);
            } else {
                totalGroups.add(Arrays.asList(individualStudent));
            }
            if(groupIsFull(groupFormed)){
                totalGroups.add(groupFormed);
                groupFormed = new ArrayList<>();
            }
        }
        if(!groupFormed.isEmpty()){
            totalGroups.add(groupFormed);
        }
        return totalGroups;
    }

    private static boolean groupIsFull(List<Student> groupFormed) {
        return !groupFormed.isEmpty() && groupFormed.stream().allMatch(student -> student.bearableGroupSize() <= groupFormed.size());
    }

    private boolean canAddToGroup(List<Student> groupFormed) {
        return this.modality.canAddToGroup(groupFormed);
    }

    private int bearableGroupSize() {
        return this.modality.maxGroupSize();
    }

    public void assignCourse(Course course) {
        this.schedules = this.schedules.stream().filter(aSchedule -> !aSchedule.equals(course.getSchedule())).collect(Collectors.toList());
    }
}
