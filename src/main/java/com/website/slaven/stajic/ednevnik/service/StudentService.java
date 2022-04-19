package com.website.slaven.stajic.ednevnik.service;


import com.website.slaven.stajic.ednevnik.model.students;

import java.util.List;

public interface StudentService {


    public List<students>findAll();
    public students findById(int theId);

    public void save(students theStudent);

    public void deleteById(int theId);
}
