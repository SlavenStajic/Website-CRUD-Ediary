package com.website.slaven.stajic.ednevnik.controller.principal;


import com.website.slaven.stajic.ednevnik.model.Parent;
import com.website.slaven.stajic.ednevnik.model.Teacher;
import com.website.slaven.stajic.ednevnik.model.User;
import com.website.slaven.stajic.ednevnik.service.ParentService;
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
public class PrincipalController {

    private int hardDeleteId;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ParentService parentService;


    private UserService userService;

    @Autowired
    public PrincipalController(UserService theUserService){
        userService= theUserService;
    }

    @GetMapping("/upravljanjenalozima")
    public String ocjeneskola(Model theModel){

        List<User> theUser= userService.findAll();

        theModel.addAttribute("users", theUser);

        return "/ediary/principal/upravljanje-nalozima";

    }
    @GetMapping("/addUser")
    public String addUser(Model theModel){

        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "/ediary/principal/user-form";

    }

    @PostMapping("/saveUser")
    public String saveUser(  @ModelAttribute("user")@Valid User theUser , BindingResult result){

        if(result.hasErrors()){
            return "/ediary/principal/user-form";
        }

        if(theUser.getRoles().isEmpty()){
            theUser.setRoles("ROLE_UNDEFINED");
        }
        if(theUser.getStatus() == null){

            theUser.setStatus("ACTIVE");
        }

        userService.save(theUser);

        return "redirect:/ednevnik/principal/upravljanjenalozima";
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("userId")int theId, Model theModel){

        User theUser = userService.findById(theId);

        theModel.addAttribute("user", theUser);


        return "/ediary/principal/user-form";

    }

    public void setHardDeleteId(int theId){
        this.hardDeleteId = theId;
    }

    @GetMapping("/hardDeleteUser")
    public String hardDelete(){

        List<Teacher> theTeacher= teacherService.findAll();
        List<Parent> theParent = parentService.findAll();

        for(Teacher t : theTeacher){
            if(t.getUser_user_id() == hardDeleteId){

                teacherService.deleteById(t.getTeacher_id());

            }
        }

        for(Parent p : theParent){
            if(p.getUser_user_id() == hardDeleteId){

                parentService.deleteById(p.getId());

            }
        }
        userService.deleteById(hardDeleteId);

        return "redirect:/ednevnik/principal/upravljanjenalozima";

    }

    @GetMapping("/deleteUser")
    public String delete(@RequestParam("userId")int theId){

        List<Teacher> theTeacher= teacherService.findAll();
        List<Parent> theParent = parentService.findAll();


        for(Teacher t : theTeacher){
            if(t.getUser_user_id() == theId){
                setHardDeleteId(theId);
                return "/ediary/principal/cant-delete-user";
            }
        }

        for(Parent p : theParent){
            if(p.getUser_user_id() == theId){
                setHardDeleteId(theId);
                return "/ediary/principal/cant-delete-user";
            }
        }
        userService.deleteById(theId);

        return "redirect:/ednevnik/principal/upravljanjenalozima";
    }

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("userId")int theId, Model theModel){

        User theUser = userService.findById(theId);

        if(theUser.getStatus().equals("ACTIVE")){

            theUser.setStatus("DISABLED");
        } else{
            theUser.setStatus("ACTIVE");
        }

        theModel.addAttribute("user", theUser);

        userService.save(theUser);

        return "redirect:/ednevnik/principal/upravljanjenalozima";
    }

}
