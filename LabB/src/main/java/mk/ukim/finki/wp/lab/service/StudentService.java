package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);

    List<Student> filterStudents(Long courseId);

    List<Course> findCoursesList(String username);
    void addCourse(Student student,Course course);
    Student findBtUsername(String username);
}
