package domain.course;

import domain.Level;

import java.util.logging.Logger;

public class CourseFactory {
    public static Course makeCourse(Level level){
        Course createdCourse = null;
        switch (level){
            case BEGINNER:
                createdCourse = new BeginnerCourse();
                break;
            case PREINTERMEDIATE:
                createdCourse =  new PreIntermediateCourse();
                break;
            case INTERMEDIATE:
                createdCourse = new Intermediate();
                break;
            case UPPERINTERMEDIATE:
                createdCourse = new UpperIntermediateCourse();
                break;
            case ADVANCED:
                createdCourse = new AdvancedCourse();
                break;
            default:
                Logger.getLogger(CourseFactory.class.getName()).warning("Error al crear curso - Level inexistente");
        }
        return createdCourse;
    }
}
