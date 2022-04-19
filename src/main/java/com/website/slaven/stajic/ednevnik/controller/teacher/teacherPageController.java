package com.website.slaven.stajic.ednevnik.controller.teacher;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/ednevnik/teacher")
public class teacherPageController implements Authentication {

    TeacherService teacherService;

    @Autowired
    public teacherPageController(TeacherService theTeacherService){
        teacherService = theTeacherService;
    }

    @Autowired
    ClassService classService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    ParentService parentService;

    @Autowired
    GradesService gradesService;

    @GetMapping("/teacher-grades")
    public String grades(Model theModel, Authentication authentication){

        String userName = "";

        if (authentication != null) {

            userName = (authentication.getName());
        }

        Teacher teacher = returnTeacher(userName);
        classes classes = returnClasses(teacher);
        List<students> theStudentsInClass = returnStudents(classes);
        List<grades> theGradesInClass = returnGrades(theStudentsInClass);

        theModel.addAttribute("students",theStudentsInClass);
        theModel.addAttribute("grades",theGradesInClass);


        return "/ediary/teacher/teacher-grades";
    }

    @GetMapping("/updateTeacherGrades")
    public String updateTeacherGrades(@RequestParam("gradesId") int theId, Model theModel){

        grades theGrades = gradesService.findById(theId);
        theModel.addAttribute("grades",theGrades);

        List<students> theStudents = studentService.findAll();
        theModel.addAttribute("student",theStudents);

        return "/ediary/teacher/teacher-grades-form";
    }

    @PostMapping("/saveTeacher-grades")
    public String saveGrades(@Valid @ModelAttribute("grades") grades theGrades, BindingResult result){

        if(result.hasErrors()){
            return "/ediary/teacher/teacher-grades-form";
        }
        gradesService.save(theGrades);
        return "redirect:/ednevnik/teacher/teacher-grades";
    }

    @GetMapping("/teacher-parents")
    public String parents(Model theModel, Authentication authentication){

        String userName = "";

        if (authentication != null) {

            userName = (authentication.getName());
        }

        Teacher teacher = returnTeacher(userName);
        classes classes = returnClasses(teacher);
        List<students> theStudentsInClass = returnStudents(classes);
        List<Parent> theParentsInClass = returnParents(theStudentsInClass);

        theModel.addAttribute("parent", theParentsInClass);

        return  "/ediary/teacher/teacher-parents";
    }

    @GetMapping("/teachers-page")
    public String teachers(Model theModel, Authentication authentication){

        String userName = "";

        if (authentication != null) {

            userName = (authentication.getName());
        }

        Teacher teacher = returnTeacher(userName);
        classes classes = returnClasses(teacher);
        List<students> theStudentsInClass = returnStudents(classes);

        theModel.addAttribute("class",classes);
        theModel.addAttribute("students", theStudentsInClass);

        return "/ediary/teacher/teacher-class";
    }

    public List returnGrades(List<students> students){

        List<grades> selectedGrades = new ArrayList<grades>();

        List<grades> theGrades = gradesService.findAll();
        for(students s: students){

            for(grades g : theGrades){

                if(s.getId() == g.getStudents_id()){
                    selectedGrades.add(g);
                }
            }

        }
        return selectedGrades;
    }

    public List returnParents(List<students> students){

        List<Parent> selectedParents = new ArrayList<Parent>();
        List<Parent> theParents = parentService.findAll();


        for(students s: students){

            for(Parent p : theParents){

                if(s.getId() == p.getStudents_id()){
                    selectedParents.add(p);
                }
            }
        }

        return selectedParents;

    }

    public Teacher returnTeacher(String userName){

        List<User> theUsers = userService.findAll();

        int userThatIsLoggedin = -1;
        Teacher teacher = null;

        for(User u : theUsers){

            if(u.getUserName().equals(userName)){
                userThatIsLoggedin = u.getId();
                break;
            }
        }
        List<Teacher> theTeacher= teacherService.findAll();

        for(Teacher t : theTeacher){

            if(t.getUser_user_id() == userThatIsLoggedin){
                teacher= t;
                break;
            }
        }
        return  teacher;
    }

    public classes returnClasses(Teacher teacher){

        classes classes = null;

        List<classes> theClasses = classService.findAll();

        for(classes c : theClasses){
            if(c.getClass_id() == teacher.getClasses_class_id()){
                classes = c;
                break;
            }
        }
        return classes;
    }

    public List returnStudents(classes classes){

        List<students> theStudentsInClass = new ArrayList<students>();
        List<students> theStudents = studentService.findAll();

        for(students s : theStudents){
            if(s.getClasses_class_id() == classes.getClass_id()){
                theStudentsInClass.add(s);
            }
        }
        return  theStudentsInClass;
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
