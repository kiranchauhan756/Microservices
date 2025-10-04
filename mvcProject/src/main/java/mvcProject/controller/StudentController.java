package mvcProject.controller;

import mvcProject.entity.Student;
import mvcProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @GetMapping
    public String getAllStudents(Model model){
        List<Student> studentList= studentService.getAllStudent();
        model.addAttribute("student",studentList);
        return "student";
    }



}
