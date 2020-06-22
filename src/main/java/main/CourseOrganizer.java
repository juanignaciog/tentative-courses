package main;

import domain.*;
import domain.course.Course;
import domain.course.CourseFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CourseOrganizer {

    public List<Course> getCourses(List<Student> students, List<Teacher> teachers){
        return createAllCourses(sortStudentsByLevel(students), teachers);
    }

    private Map<Level, List<Student>> sortStudentsByLevel(List<Student> enrolledStudents) {
        return enrolledStudents.stream().collect(Collectors.groupingBy(Student::getLevel));
    }

    private List<Course> createAllCourses(Map<Level, List<Student>>sortedStudents, List<Teacher> availableTeachers) {
        List<Course> totalCourses = new ArrayList<>();
        for(Map.Entry<Level, List<Student>> courseLevel : sortedStudents.entrySet()){
            List<Course> sameLevelCourses = createCourse(availableTeachers, courseLevel.getKey(), courseLevel.getValue());
            sameLevelCourses = sameLevelCourses.stream().filter(course -> course.getTeacher() != null).collect(Collectors.toList());
            totalCourses.addAll(sameLevelCourses);
        }
        return totalCourses;
    }
    
    private List<Course> createCourse(List<Teacher> availableTeachers, Level level, List<Student> courseStudents) {
        List<Course> courses = new ArrayList<>();
        List<List<Student>> studentsSplitInGroups = Student.formGroups(courseStudents);
        for(List<Student> groupStudents:studentsSplitInGroups){
            Course course = CourseFactory.makeCourse(level);
            course.assignTeacher(groupStudents, availableTeachers);
            courses.add(course);
        }
        return courses;
    }
    

}
