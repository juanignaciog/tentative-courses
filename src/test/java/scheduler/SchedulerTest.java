package scheduler;

import domain.schedule.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import domain.schedule.Scheduler;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SchedulerTest {
    private List<Schedule> studentSchedules;
    private List<Schedule> teacherSchedules;
    @BeforeEach
    public void setUp(){
        studentSchedules = new LinkedList<>(Arrays.asList(new Schedule(Schedule.DAY.MONDAY, LocalTime.of(15, 0)),
                new Schedule(Schedule.DAY.THURSDAY, LocalTime.of(17,30)),
                new Schedule(Schedule.DAY.FRIDAY, LocalTime.of(18,0))));
        teacherSchedules = new LinkedList<>(Arrays.asList(new Schedule(Schedule.DAY.THURSDAY, LocalTime.of(17,30))));
    }
    
    @AfterEach
    public void tearDown(){
        studentSchedules.clear();
        teacherSchedules.clear();
    }

    @Test
    public void haveOnlyOneScheduleInCommon(){

        List<Schedule> availableSchedules = Scheduler.findCommonSchedule(Arrays.asList(studentSchedules, teacherSchedules));
        assertEquals(availableSchedules.get(0), teacherSchedules.get(0));
    }
    
    @Test
    public void haveSeveralSchedulesInCommon(){
        teacherSchedules.add(new Schedule(Schedule.DAY.FRIDAY, LocalTime.of(18,0)));
    
        List<Schedule> availableSchedules = Scheduler.findCommonSchedule(Arrays.asList(teacherSchedules, teacherSchedules));
        assertEquals(2, availableSchedules.size());
        assertEquals(availableSchedules.get(0), teacherSchedules.get(0));
        assertEquals(availableSchedules.get(1), studentSchedules.get(2));
    }
    
    @Test
    public void cannotFindScheduleInCommon(){
        studentSchedules.remove(new Schedule(Schedule.DAY.THURSDAY, LocalTime.of(17,30)));
    
        List<Schedule> availableSchedules = Scheduler.findCommonSchedule(Arrays.asList(studentSchedules, teacherSchedules));
        assertTrue(availableSchedules.isEmpty());
    }
    
    @Test
    public void manyStudentsAndManyTeachers(){
        List<Schedule> teacher2Schedules = new LinkedList<>(Arrays.asList(
                new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(12,0)),
                new Schedule(Schedule.DAY.TUESDAY, LocalTime.of(16,30))
        ));
        List<Schedule> anotherStudentSchedules = new LinkedList<>(Arrays.asList(
                new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(12,0)),
                new Schedule(Schedule.DAY.FRIDAY, LocalTime.of(16,30)),
                new Schedule(Schedule.DAY.FRIDAY, LocalTime.of(18,0))
        ));
        studentSchedules.add(new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(12,0)));
        teacherSchedules.add(new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(12,0)));
        
        List<Schedule> availableSchedules = Scheduler.findCommonSchedule(Arrays.asList(studentSchedules, teacherSchedules, teacher2Schedules, anotherStudentSchedules));
        assertEquals(1,availableSchedules.size());
        assertEquals(new Schedule(Schedule.DAY.WEDNESDAY, LocalTime.of(12,0)), availableSchedules.get(0));
    }
}
