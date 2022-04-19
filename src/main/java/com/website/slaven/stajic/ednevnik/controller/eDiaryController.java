package com.website.slaven.stajic.ednevnik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ednevnik")
public class eDiaryController {

    @GetMapping("/pocetna")
    public String pocetna() {
        return "/ediary/ediaryHome";
    }
}
