package com.website.slaven.stajic.ednevnik.service;


import com.website.slaven.stajic.ednevnik.model.classes;
import com.website.slaven.stajic.repository.classes.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService{

    ClassesRepository classesRepository;

    @Autowired
    public ClassServiceImpl(ClassesRepository theClassesRepository){
        this.classesRepository = theClassesRepository;
    }

    @Override
    public List<classes> findAll() {
        return classesRepository.findAll();
    }

    @Override
    public classes findById(int theId) {
        Optional<classes> result = classesRepository.findById(theId);

        classes theClasses = null;

        if(result.isPresent()){
            theClasses = result.get();
        } else{
            throw new RuntimeException("Did not find student id - " + theId);

        }
        return  theClasses;
    }

    @Override
    public void save(classes theClasses) {
    classesRepository.save(theClasses);
    }

    @Override
    public void deleteById(int theId) {
    classesRepository.deleteById(theId);
    }
}
