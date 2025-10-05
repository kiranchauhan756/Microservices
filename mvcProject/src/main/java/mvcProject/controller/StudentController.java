package mvcProject.controller;

import mvcProject.entity.Student;
import mvcProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @GetMapping()
    public String getAllStudents(Model model){
        List<Student> studentList= studentService.getAllStudent();
        model.addAttribute("student",studentList);
        return "student";
    }

    @GetMapping("/showFormForAdd")
    public String addStudent(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "showFormForAdd";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int id,Model model){
        Student student=studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "/showFormForAdd";
    }
    @GetMapping("/delete")
    public String showFormForDelete(@RequestParam("studentId") int id,Model model){
        studentService.deleteStudent(id);
        return "redirect:/student";
    }




}
