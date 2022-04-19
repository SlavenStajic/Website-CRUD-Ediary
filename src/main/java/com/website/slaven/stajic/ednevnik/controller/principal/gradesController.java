package com.website.slaven.stajic.ednevnik.controller.principal;

import com.website.slaven.stajic.ednevnik.model.*;
import com.website.slaven.stajic.ednevnik.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/ednevnik/principal")
public class gradesController implements Authentication {

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    ParentService parentService;

    GradesService gradesService;

    @Autowired
    public gradesController(GradesService theGradesService){
        this.gradesService = theGradesService;
    }

    @GetMapping("/grades")
    public String grades(Model theModel){

        List<students> theStudents = studentService.findAll();
        theModel.addAttribute("student",theStudents);

        List<grades> theGrades = gradesService.findAll();
        theModel.addAttribute("grades",theGrades);

        return "/ediary/principal/grades";
    }

    @GetMapping("/updateGrades")
    public String updateGrades(@RequestParam("gradesId") int theId, Model theModel){

        grades theGrades = gradesService.findById(theId);
        theModel.addAttribute("grades",theGrades);

        List<students> theStudents = studentService.findAll();
        theModel.addAttribute("student",theStudents);

        return "/ediary/principal/grades-form";
    }

    @PostMapping("/saveGrades")
    public String saveGrades(@Valid  @ModelAttribute("grades")grades theGrades, BindingResult result){


        if(result.hasErrors()){
            return "/ediary/teacher/teacher-grades-form";
        }


             gradesService.save(theGrades);

        return "redirect:/ednevnik/principal/grades";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}

