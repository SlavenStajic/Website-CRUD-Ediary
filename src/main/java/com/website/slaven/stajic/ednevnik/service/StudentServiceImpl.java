package com.website.slaven.stajic.ednevnik.service;

import com.website.slaven.stajic.ednevnik.model.User;
import com.website.slaven.stajic.ednevnik.model.students;
import com.website.slaven.stajic.repository.students.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepository){
        this.studentRepository = theStudentRepository;
    }

    @Override
    public List<students> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public students findById(int theId) {
        Optional<students> result = studentRepository.findById(theId);

        students theStudents = null;

        if(result.isPresent()){
            theStudents = result.get();

        } else{
            throw new RuntimeException("Did not find student id - " + theId);
        }

        return theStudents;
    }

    @Override
    public void save(students theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public void deleteById(int theId) {
    studentRepository.deleteById(theId);
    }
}
