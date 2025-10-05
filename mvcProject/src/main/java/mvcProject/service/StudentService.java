package mvcProject.service;

import mvcProject.entity.Student;
import mvcProject.exception.StudentNotFoundException;
import mvcProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

   private StudentRepository studentRepository;

   @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent(){
       return studentRepository.findAllByOrderByLastNameAsc();
    }

    public Student getStudentById(Integer id){
       Optional<Student> student=studentRepository.findById(Long.valueOf(id));
       if(student.isPresent())
           return student.get();
        throw new StudentNotFoundException("Student not found");

    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id){
       studentRepository.deleteById(Long.valueOf(id));
    }

}
