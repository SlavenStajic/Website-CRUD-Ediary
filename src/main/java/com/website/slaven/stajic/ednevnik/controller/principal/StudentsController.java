package com.website.slaven.stajic.ednevnik.controller.principal;


import com.website.slaven.stajic.ednevnik.model.*;
import com.website.slaven.stajic.ednevnik.service.ClassService;
import com.website.slaven.stajic.ednevnik.service.GradesService;
import com.website.slaven.stajic.ednevnik.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ednevnik/principal")
public class StudentsController {

    @Autowired
    private GradesService gradesService;

    @Autowired
    private ClassService classService;


    private StudentService studentService;

    @Autowired
    private StudentsController(StudentService theStudentService){
        this.studentService = theStudentService;
    }

    @GetMapping("/students")
    public String izostanciskole(Model theModel){

        List<students> theStudents = studentService.findAll();

        theModel.addAttribute("student",theStudents);
        return "/ediary/principal/students";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model theModel){

        List<classes> theClasses = classService.findAll();
        theModel.addAttribute("theClasses", theClasses);

        students theStudents = new students();
        theModel.addAttribute("student", theStudents);

        return "/ediary/principal/student-form";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam("studentId")int theId, Model theModel){

        List<classes> theClasses = classService.findAll();
        theModel.addAttribute("theClasses", theClasses);

        students theStudents = studentService.findById(theId);
        theModel.addAttribute("student", theStudents);

        List<grades> theGrades = gradesService.findAll();
        theModel.addAttribute("grades",theGrades);

        return "/ediary/principal/student-form";

    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")@Valid students theStudents , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/ediary/principal/student-form";
        }

        List<classes> theClasses = classService.findAll();
        boolean result = false;

        for(classes c : theClasses){
            if(c.getClass_id() == theStudents.getClasses_class_id()){
                result = true;
                break;
            } else{
                result= false;
            }
        }

        if(result){
            grades theGrades = new grades();
            studentService.save(theStudents);
            theGrades.setId(theStudents.getId());
            theGrades.setStudents_id(theStudents.getId());

            gradesService.save(theGrades);

            return "redirect:/ednevnik/principal/students";

        }
        return "/ediary/principal/wrong-id-student";
    }

    @GetMapping("/deleteStudent")
    public String delete(@RequestParam("studentId") int theId){

        gradesService.deleteById(theId);

        studentService.deleteById(theId);

        return "redirect:/ednevnik/principal/students";
    }
}
