package com.website.slaven.stajic.ednevnik.service;

import com.website.slaven.stajic.ednevnik.model.Parent;

import java.util.List;

public interface ParentService {

    public List<Parent> findAll();

    public Parent findById(int theId);

    public void save(Parent theParent);

    public void deleteById(int theId);


}
