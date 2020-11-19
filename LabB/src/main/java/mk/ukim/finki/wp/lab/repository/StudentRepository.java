package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    final List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("todorht", "tode", "Todor", "Todorovski", null));
        students.add(new Student("pero", "tode", "Petar", "Stojkov", null));
        students.add(new Student("igor", "tode", "Igor", "Maksimov", null));
        students.add(new Student("joco", "tode", "Jordan", "Ivanov", null));
        students.add(new Student("temel", "tode", "Kristijan", "Temelkovski", null));
    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(r->r.getName().equals(text) || r.getSurname().equals(text)).collect(Collectors.toList());
    }

    public Student findByUsername(String username){
        return students.stream().filter(r->r.getUsername().equals(username)).findFirst().get();
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public Student addCourse(Student student, Course course){
        student.getCourseList().add(course);
        return student;
    }

    public List<Course> findCourses(String username){
        return this.findByUsername(username).getCourseList();
    }
}
