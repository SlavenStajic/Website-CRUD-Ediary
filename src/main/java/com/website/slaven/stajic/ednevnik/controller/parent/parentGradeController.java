package com.website.slaven.stajic.ednevnik.controller.parent;

import com.website.slaven.stajic.ednevnik.model.Parent;
import com.website.slaven.stajic.ednevnik.model.User;
import com.website.slaven.stajic.ednevnik.model.grades;
import com.website.slaven.stajic.ednevnik.model.students;
import com.website.slaven.stajic.ednevnik.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ednevnik/parent")
public class parentGradeController {

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
    public parentGradeController(GradesService theGradesService){
        this.gradesService = theGradesService;
    }

    @GetMapping("/parent-grades")
    public String parentgrades(Model theModel, Authentication authentication){

        Parent parent = null;
        grades grades = null;
        students students = null;
        String userName = "";
        int userThatIsLoggedin = -1;

        if (authentication != null) {
            userName = (authentication.getName());
        }

        List<User> theUsers = userService.findAll();

        for(User u : theUsers){
            if(u.getUserName().equals(userName)){
                userThatIsLoggedin = u.getId();
                break;
            }
        }

        List<Parent> theParents = parentService.findAll();

        for(Parent p : theParents){
            if(p.getUser_user_id() == userThatIsLoggedin){
                parent = p;
                break;
            }
        }

        List<grades> theGrades = gradesService.findAll();

        if(parent != null) {
            for (grades g : theGrades) {
                if (g.getId() == parent.getStudents_id()) {
                    grades = g;
                    break;

                }
            }
        }

        List<students> theStudents = studentService.findAll();

        for(students s : theStudents){
            if(s.getId() == parent.getStudents_id()){
                students = s;
                break;
            }
        }

        theModel.addAttribute("student",students);
        theModel.addAttribute("grade",grades);
        theModel.addAttribute("parent",parent);

        return "/ediary/parent/parent-grades";
    }
}
