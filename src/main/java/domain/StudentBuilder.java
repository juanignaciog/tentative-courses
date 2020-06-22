package domain;

import domain.modality.Modality;
import domain.schedule.Schedule;

import java.util.List;

public class StudentBuilder {

    private static String name;
    private static Level level;
    private static Modality modality;
    private static List<Schedule> schedules;

    public static void setName(String name){
        StudentBuilder.name = name;
    }

    public static void setLevel(Level level){
        StudentBuilder.level = level;
    }

    public static void setModality(Modality modality){
        StudentBuilder.modality = modality;
    }
    
    public static void setSchedules(List<Schedule> schedules){
        StudentBuilder.schedules = schedules;
    }

    public static Student getStudent(){
        return new Student(name, modality, schedules, level);
    }
}
