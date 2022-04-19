package com.website.slaven.stajic.ednevnik.controller.principal;


import com.website.slaven.stajic.ednevnik.model.Parent;
import com.website.slaven.stajic.ednevnik.model.Teacher;
import com.website.slaven.stajic.ednevnik.model.classes;
import com.website.slaven.stajic.ednevnik.model.students;
import com.website.slaven.stajic.ednevnik.service.ClassService;
import com.website.slaven.stajic.ednevnik.service.GradesService;
import com.website.slaven.stajic.ednevnik.service.StudentService;
import com.website.slaven.stajic.ednevnik.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ednevnik/principal")
public class classesController {

    private int hardDeleteId;

    @Autowired
    GradesService gradesService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    ClassService classService;

    @Autowired
    public classesController(ClassService theClassService){

        this.classService = theClassService;
    }

    @GetMapping("/classes")
    public String getClasses(Model theModel){

        List<classes> theClasses = classService.findAll();

        theModel.addAttribute("theClasses", theClasses);

        return "/ediary/principal/classes";
    }

    @GetMapping("addClasses")
    public String addClasses(Model theModel){

        classes theClasses = new classes();

        theModel.addAttribute("classes" , theClasses);

        return "/ediary/principal/classes-form";
    }

    @PostMapping("/saveClasses")
    public String saveClasses(@ModelAttribute("classes")@Valid classes theClasses, BindingResult result){

        if(result.hasErrors()){
            return "/ediary/principal/classes-form";
        }



        classService.save(theClasses);

        return "redirect:/ednevnik/principal/classes";
    }

    public void setHardDeleteId(int theId){
        this.hardDeleteId = theId;
    }

    @GetMapping("/updateClasses")
    public String updateClasses(@RequestParam("classesId") int theId, Model theModel){

        classes theClasses = classService.findById(theId);

        theModel.addAttribute("classes", theClasses);

        return "/ediary/principal/classes-form";
    }

    @GetMapping("/deleteClasses")
    public String delete(@RequestParam("classesId") int theId){

        List<students> theStudents = studentService.findAll();
        List<Teacher> theTeacher= teacherService.findAll();


        for(students s : theStudents){
            if(s.getClasses_class_id() == theId){
                setHardDeleteId(theId);
                return "/ediary/principal/cant-delete-class";
            }
        }


        for(Teacher t : theTeacher){
            if(t.getClasses_class_id() == theId){
                setHardDeleteId(theId);
                return "/ediary/principal/cant-delete-class";
            }
        }

        classService.deleteById(theId);

        return "redirect:/ednevnik/principal/classes";
    }

    @GetMapping("/hardDeleteClass")
    public String hardDelete(){

        List<Teacher> theTeacher= teacherService.findAll();
        List<students> theStudent = studentService.findAll();

        for(Teacher t : theTeacher){
            if(t.getClasses_class_id() == hardDeleteId){

                teacherService.deleteById(t.getTeacher_id());


            }
        }

        for(students s : theStudent){
            if(s.getClasses_class_id() == hardDeleteId){

                gradesService.deleteById(s.getId());
                studentService.deleteById(s.getId());



            }
        }
        classService.deleteById(hardDeleteId);

        return "redirect:/ednevnik/principal/upravljanjenalozima";


    }


}
