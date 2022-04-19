package com.website.slaven.stajic.ednevnik.service;


import com.website.slaven.stajic.ednevnik.model.Parent;
import com.website.slaven.stajic.repository.parent.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService{

    ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository theParentRepository){
        this.parentRepository = theParentRepository;
    }


    @Override
    public List<Parent> findAll() {
        return  parentRepository.findAll();
    }

    @Override
    public Parent findById(int theId) {
        Optional<Parent> result = parentRepository.findById(theId);

        Parent theParent = null;

        if(result.isPresent()){
            theParent = result.get();
        } else{
            throw new RuntimeException("Did not find Parent id - " + theId);

        }
        return theParent;
    }

    @Override
    public void save(Parent theParent) {
        parentRepository.save(theParent);
    }

    @Override
    public void deleteById(int theId) {
        parentRepository.deleteById(theId);
    }
}
