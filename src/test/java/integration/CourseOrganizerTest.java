package integration;

import main.CourseOrganizer;
import domain.*;
import domain.course.Course;
import domain.modality.Group;
import domain.modality.Solo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.schedule.Schedule;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseOrganizerTest {

    CourseOrganizer courseOrganizer;
    List<Student> enrolled = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    Schedule mondayThreePm = new Schedule(Schedule.DAY.MONDAY, LocalTime.of(15, 0));
    Schedule mondayTenAm = new Schedule(Schedule.DAY.MONDAY, LocalTime.of(10, 0));
    Schedule tuesdayElevenThirty = new Schedule(Schedule.DAY.TUESDAY, LocalTime.of(11, 30));
    Schedule wednesdayNineAm = new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(9,0));
    Schedule wednesdayTenThirtyAm = new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(10,30));
    Schedule wednesdaySixPm = new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(18,0));
    Schedule thursdaySixThirtyPm = new Schedule(Schedule.DAY.THURSDAY, LocalTime.of(17,30));
    Schedule thursdayTenThirtyAm = new Schedule(Schedule.DAY.THURSDAY, LocalTime.of(10,30));
    Schedule fridaySixPm = new Schedule(Schedule.DAY.FRIDAY, LocalTime.of(18,0));
    
    List<Schedule> student1Schedules = new LinkedList<>(Arrays.asList(mondayThreePm,thursdaySixThirtyPm,fridaySixPm));
    List<Schedule> student2Schedules = new LinkedList<>(Arrays.asList(mondayTenAm, thursdayTenThirtyAm,fridaySixPm,thursdaySixThirtyPm));
    List<Schedule> student3Schedules = new LinkedList<>(Arrays.asList(mondayTenAm,wednesdayTenThirtyAm,wednesdaySixPm,thursdaySixThirtyPm));
    List<Schedule> student4Schedules = new LinkedList<>(Arrays.asList(wednesdayNineAm));
    List<Schedule> student5Schedules = new LinkedList<>(Arrays.asList(mondayTenAm,wednesdayNineAm));
    
    List<Schedule> teacher1Schedules = new LinkedList<>(Arrays.asList(tuesdayElevenThirty,wednesdayNineAm));
    List<Schedule> teacher2Schedules = new LinkedList<>(Arrays.asList(thursdaySixThirtyPm));

    @BeforeEach
    public void setUp(){
        StudentBuilder.setLevel(Level.BEGINNER);
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("juani");
        StudentBuilder.setSchedules(student1Schedules);
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("pepe");
        StudentBuilder.setSchedules(student2Schedules);
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("mica");
        StudentBuilder.setSchedules(student3Schedules);
        enrolled.add(StudentBuilder.getStudent());
        
        teachers.add(new Teacher(teacher1Schedules));
        teachers.add(new Teacher(teacher2Schedules));
        courseOrganizer = new CourseOrganizer();
    }
    
    @AfterEach
    public void tearDown(){
        enrolled.clear();
        teachers.clear();
    }

    @Test
    public void getAvailableCourses(){
        assertNotNull(courseOrganizer.getCourses( enrolled, teachers));
    }

    @Test
    public void courseHasProperTeacher(){
        List<Course> availableCourses = courseOrganizer.getCourses( enrolled, teachers);
        Course firstAvailableCourse = availableCourses.get(0);
        assertEquals(teachers.get(1), firstAvailableCourse.getTeacher());
    }

    @Test
    public void courseHasProperLevel(){
        List<Course> availableCourses = courseOrganizer.getCourses( enrolled, teachers);
        Course firstAvailableCourse = availableCourses.get(0);
        assertEquals(Level.BEGINNER, firstAvailableCourse.getLevel());
    }
    
    @Test
    public void courseHasOnlyPossibleSchedule(){
        List<Course> availableCourses = courseOrganizer.getCourses( enrolled, teachers);
        Course firstAvailableCourse = availableCourses.get(0);
        assertEquals(thursdaySixThirtyPm,
                firstAvailableCourse.getSchedule());
    }
    
    @Test
    public void begginerAndAdvancedCourse(){
        StudentBuilder.setLevel(Level.ADVANCED);
        StudentBuilder.setName("martin");
        StudentBuilder.setSchedules(student4Schedules);
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("jorge");
        StudentBuilder.setSchedules(student5Schedules);
        enrolled.add(StudentBuilder.getStudent());
    
        List<Course> availableCourses = courseOrganizer.getCourses( enrolled, teachers);
        Course advancedLevelCourse = availableCourses.get(0);
        assertEquals(2, availableCourses.size());
        assertEquals(Level.ADVANCED, advancedLevelCourse.getLevel());
        assertEquals(new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(9,0)),
                advancedLevelCourse.getSchedule());
    }
    
    @Test
    public void courseWithSixAndCourseWithTwo(){
        StudentBuilder.setName("martin");
        StudentBuilder.setSchedules(new LinkedList<>(Arrays.asList(thursdaySixThirtyPm)));
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("jorge");
        StudentBuilder.setSchedules(new LinkedList<>(Arrays.asList(
                new Schedule(Schedule.DAY.TUESDAY, LocalTime.of(14,0)),
                thursdaySixThirtyPm
        )));
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("joaquin");
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("martin");
        StudentBuilder.setSchedules(student4Schedules);
        enrolled.add(StudentBuilder.getStudent());
        StudentBuilder.setName("sofia");
        StudentBuilder.setSchedules(student5Schedules);
        enrolled.add(StudentBuilder.getStudent());
    
        List<Course> availableCourses = courseOrganizer.getCourses(enrolled, teachers);
        Course beginnersLevelCourse = availableCourses.get(0);
        
        assertEquals(thursdaySixThirtyPm, beginnersLevelCourse.getSchedule());
        assertEquals(2, availableCourses.size());
        assertEquals(6, beginnersLevelCourse.getEnrolled().size());
    }

    @Test
    public void teacherGetsCourseAndCantGetInNextOne(){
        StudentBuilder.setName("alumnoConMismosHorariosQueMica");
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setSchedules(student3Schedules);
        enrolled.add(StudentBuilder.getStudent());
    
        List<Course> availableCourses = courseOrganizer.getCourses(enrolled, teachers);
        assertEquals(1, availableCourses.size());
        
        assertEquals(availableCourses.get(0).getSchedule(), thursdaySixThirtyPm);
    }
}
