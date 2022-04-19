package com.website.slaven.stajic.ednevnik.controller.principal;


import com.website.slaven.stajic.ednevnik.model.Teacher;
import com.website.slaven.stajic.ednevnik.model.User;
import com.website.slaven.stajic.ednevnik.model.classes;
import com.website.slaven.stajic.ednevnik.model.students;
import com.website.slaven.stajic.ednevnik.service.ClassService;
import com.website.slaven.stajic.ednevnik.service.TeacherService;
import com.website.slaven.stajic.ednevnik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ednevnik/principal")
public class teachersController {

    TeacherService teacherService;

    @Autowired
    public teachersController(TeacherService theTeacherService){
        teacherService = theTeacherService;
    }

    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;

    @GetMapping("/teachers")
    public String teachers(Model theModel){

        List<Teacher> theTeacher= teacherService.findAll();

        theModel.addAttribute("teacher", theTeacher);

        return "/ediary/principal/teachers";
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model theModel){

        Teacher theTeacher = new Teacher();
        theModel.addAttribute("teacher", theTeacher);

        List<User> theUser= userService.findAll();
        theModel.addAttribute("users", theUser);

        List<classes> theClasses = classService.findAll();
        theModel.addAttribute("theClasses", theClasses);

        return "/ediary/principal/teacher-form";
    }

    @GetMapping("/updateTeacher")
    public String updateTeacher(@RequestParam("teacherId")int theId, Model theModel){

        Teacher theTeacher = teacherService.findById(theId);
        theModel.addAttribute("teacher", theTeacher);

        List<User> theUser= userService.findAll();
        theModel.addAttribute("users", theUser);

        List<classes> theClasses = classService.findAll();
        theModel.addAttribute("theClasses", theClasses);

        return "/ediary/principal/teacher-form";

    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher")@Valid Teacher theTeacher , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/ediary/principal/teacher-form";
        }

        List<User> theUser= userService.findAll();
        List<classes> theClasses = classService.findAll();

        boolean result = false;
        boolean result2 = false;

        for(User u : theUser) {
            if (u.getId() == theTeacher.getUser_user_id()) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        for(classes c : theClasses){
            if(c.getClass_id() == theTeacher.getClasses_class_id()){
                result2 = true;
                break;
            } else{
                result2= false;
            }
        }
        if(result && result2) {

                teacherService.save(theTeacher);

                return "redirect:/ednevnik/principal/teachers";

        }
        return "/ediary/principal/wrong-id";
    }

    @GetMapping("/deleteTeacher")
    public String delete(@RequestParam("teacherId") int theId){

        teacherService.deleteById(theId);

        return "redirect:/ednevnik/principal/teachers";
    }
}
