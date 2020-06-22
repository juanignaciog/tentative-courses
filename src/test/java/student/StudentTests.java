package student;

import domain.Student;
import domain.StudentBuilder;
import domain.modality.Group;
import domain.modality.Solo;
import domain.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentTests {

    List<Student> totalStudents = new ArrayList<>();
    List<List<Student>> posibleCourses;

    @BeforeAll
    public static void setUpInicial(){
        StudentBuilder.setLevel(Level.BEGINNER);
    }

    @BeforeEach
    public void setUp(){
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("juancito");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("pepe");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("mica");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("flor");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("martin");
        totalStudents.add(StudentBuilder.getStudent());
    }

    @AfterEach
    public void tearDown(){
        posibleCourses.clear();
    }

    @Test
    public void canAddToGroup(){
        StudentBuilder.setName("anto");
        totalStudents.add(StudentBuilder.getStudent());
        posibleCourses = Student.formGroups(totalStudents);
        assertEquals(1, posibleCourses.size());
    }

    @Test
    public void groupIsFull(){
        StudentBuilder.setName("anto");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("fer");
        totalStudents.add(StudentBuilder.getStudent());
        posibleCourses = Student.formGroups(totalStudents);
        assertEquals(2, posibleCourses.size());
    }

    @Test
    public void lonerStudentDoesNotAdd(){
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("han");
        totalStudents.add(StudentBuilder.getStudent());
        posibleCourses = Student.formGroups(totalStudents);
        assertEquals(2, posibleCourses.size());
        List<Student> soloGroup = posibleCourses.get(0);
        assertEquals(1, soloGroup.size());
        List<Student> previousGroup = posibleCourses.get(1); //al no completarse, el grupo se agrego ultimo
        assertEquals(5, previousGroup.size());
    }

    @Test
    public void mixedStudents(){
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("han");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("agustina");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("llanero");
        totalStudents.add(StudentBuilder.getStudent());
        posibleCourses= Student.formGroups(totalStudents);
        assertEquals(3, posibleCourses.size());
    }

    @Test
    public void noStudents(){
        posibleCourses = Student.formGroups(Arrays.asList());
        assertEquals(0, posibleCourses.size());
    }
    
    @Test
    public void mixedStudents2(){
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("han");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("agustina");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("llanero");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("pablo");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Solo());
        StudentBuilder.setName("leandro");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setModality(new Group());
        StudentBuilder.setName("liz");
        totalStudents.add(StudentBuilder.getStudent());
        StudentBuilder.setName("camila");
        totalStudents.add(StudentBuilder.getStudent());
        posibleCourses= Student.formGroups(totalStudents);
        assertEquals(5, posibleCourses.size());
    }


}
