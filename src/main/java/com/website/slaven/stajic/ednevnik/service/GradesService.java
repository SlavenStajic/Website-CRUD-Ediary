package com.website.slaven.stajic.ednevnik.service;


import com.website.slaven.stajic.ednevnik.model.grades;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface GradesService {

    public List<grades> findAll();

    public grades findById(int theId);

    public void save(grades theGrades);

    public void deleteById(int theId);



}
