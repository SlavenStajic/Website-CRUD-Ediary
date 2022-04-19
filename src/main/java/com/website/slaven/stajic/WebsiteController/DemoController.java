package com.website.slaven.stajic.WebsiteController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {


    @GetMapping(value = {"/", "/index"})
    public String showHomePage(){
        return "/website/index";
    }

    @GetMapping(value="/resume")
    public String showResumePage(){
        return "/website/resume";
    }

    @GetMapping(value="/projects")
    public String showProjectsPage(){
        return "/website/projects";
    }






}
