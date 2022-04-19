package com.website.slaven.stajic.ednevnik.service;

import com.website.slaven.stajic.ednevnik.model.grades;
import com.website.slaven.stajic.repository.grades.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GradesServiceImpl implements GradesService {


    private String userName;

    GradesRepository gradesRepository;

    @Autowired
    public GradesServiceImpl(GradesRepository theGradesRepository){
        this.gradesRepository = theGradesRepository;
    }

    @Override
    public List<grades> findAll() {
       return gradesRepository.findAll();
    }

    @Override
    public grades findById(int theId) {

        Optional<grades> result = gradesRepository.findById(theId);

        grades theGrades = null;

        if(result.isPresent()){
            theGrades = result.get();
        } else{
            throw new RuntimeException("Did not find grade id - " + theId);
        }

        return theGrades;
    }

    @Override
    public void save(grades theGrades) {
        gradesRepository.save(theGrades);
    }

    @Override
    public void deleteById(int theId) {
        gradesRepository.deleteById(theId);

    }






}
