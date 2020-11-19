package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepository implements Serializable {

    List<Course> courses = new ArrayList<>();

    public final StudentRepository studentRepository;

    public CourseRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init(){
        courses.add(new Course("Web programiranje","Web programiranje", null,null));
        courses.add(new Course("Napredno programiranje","Napredno programiranje", null,null));
        courses.add(new Course("Mobilni platformi i programiranje","Mobilni platformi i programiranje", null,null));
        courses.add(new Course("Algoritmi i podatochni strukturi","Algoritmi i podatochni strukturi", null, null));
        courses.add(new Course("Optichki mrezi i tehnologii","Optichki mrezi i tehnologii", null, null));
    }

    public List<Course> findAllCourses(){
        return courses.stream().sorted(Comparator.comparing(course -> course.getName().toLowerCase())).collect(Collectors.toList());
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst().get().getStudents();
    }

    public Course addStudentToCourse(Student student, Long courseId){
        Course course = findById(courseId);
        course.getStudents().add(student);
        return course;
    }

    public Course findById(Long id){
        return courses.stream().filter(r->r.getCourseId().equals(id)).findFirst().orElse(null);
    }

    public void deleteById(Long id){
        courses.removeIf(c->c.getCourseId().equals(id));
    }

    public Course findByName(String name){
        return courses.stream().filter(c->c.getName().equals(name)).findFirst().orElse(null);
    }

    public Course save(String name, String description, Teacher teacher){
            if(courses.stream().anyMatch(c->c.getName().equals(name))){
                return null;
            }else {
                Course course = new Course(name, description, null, teacher);
                courses.add(course);
                return course;
            }
        }


}
