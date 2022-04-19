package com.website.slaven.stajic.ednevnik.service;

import com.website.slaven.stajic.ednevnik.model.classes;

import java.util.List;


public interface ClassService {

    public List<classes> findAll();
    public classes findById(int theId);

    public void save(classes theClasses);

    public void deleteById(int theId);
}
