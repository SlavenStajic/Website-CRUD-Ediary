package com.website.slaven.stajic.ednevnik.service;


import com.website.slaven.stajic.ednevnik.model.Teacher;
import com.website.slaven.stajic.repository.Teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository theTeacherRepository){
        this.teacherRepository = theTeacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int theId) {

        Optional<Teacher> result = teacherRepository.findById(theId);

        Teacher theTeacher = null;

        if(result.isPresent()){
            theTeacher = result.get();

        } else{
            throw new RuntimeException("Did not find teacher id - " + theId);
        }

        return theTeacher;
    }

    @Override
    public void save(Teacher theTeacher) {
        teacherRepository.save(theTeacher);
    }

    @Override
    public void deleteById(int theId) {
        teacherRepository.deleteById(theId);
    }
}
