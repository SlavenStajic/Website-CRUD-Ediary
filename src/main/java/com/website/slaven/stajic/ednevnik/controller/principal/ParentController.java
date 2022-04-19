package com.website.slaven.stajic.ednevnik.controller.principal;

import com.website.slaven.stajic.ednevnik.model.*;
import com.website.slaven.stajic.ednevnik.service.ClassService;
import com.website.slaven.stajic.ednevnik.service.ParentService;
import com.website.slaven.stajic.ednevnik.service.StudentService;
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
public class ParentController {

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    ParentService parentService;

    @Autowired
    public ParentController(ParentService theParentService){
        this.parentService = theParentService;
    }

    @GetMapping("/parents")
    public String parents(Model theModel){

        List<Parent> theParent = parentService.findAll();




        theModel.addAttribute("parent",theParent);

        return "/ediary/principal/parents";
    }

    @GetMapping("/addParent")
    public String addParent(Model theModel){

        Parent theParent = new Parent();
        theModel.addAttribute("parent", theParent);

        List<User> theUser= userService.findAll();
        theModel.addAttribute("users", theUser);


        List<students> theStudents = studentService.findAll();
        theModel.addAttribute("student",theStudents);


        return "/ediary/principal/parent-form";
    }

    @GetMapping("/updateParent")
    public String updateParent(@RequestParam("parentId") int theId, Model theModel){

        Parent theParent = parentService.findById(theId);
        theModel.addAttribute("parent",theParent);

        List<User> theUser= userService.findAll();
        theModel.addAttribute("users", theUser);

        List<students> theStudents = studentService.findAll();
        theModel.addAttribute("student",theStudents);


        return "/ediary/principal/parent-form";
    }

    @PostMapping("/saveParent")
    public String saveParent(@ModelAttribute("parent")@Valid Parent theParent , BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            return "/ediary/principal/parent-form";
        }

        List<User> theUser= userService.findAll();
        List<students> theStudents = studentService.findAll();

        boolean result = false;
        boolean result2 = false;

        for(User u : theUser) {
            if (u.getId() == theParent.getUser_user_id()) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        for(students s : theStudents){
            if(s.getId() == theParent.getStudents_id()){
                result2 = true;
                break;
            } else{
                result2= false;
            }
        }
        if(result && result2) {

            parentService.save(theParent);

            return "redirect:/ednevnik/principal/parents";

        }
        return "/ediary/principal/wrong-id-parent";



    }

    @GetMapping("/deleteParent")
    public String deleteParent(@RequestParam("parentId")int theId){

        parentService.deleteById(theId);

        return "redirect:/ednevnik/principal/parents";
    }

}
