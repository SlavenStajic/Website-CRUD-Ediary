package com.website.slaven.stajic.ednevnik.service;

import com.website.slaven.stajic.ednevnik.model.Teacher;



import java.util.List;

public interface TeacherService {

    public List<Teacher> findAll();

    public Teacher findById(int theId);

    public void save(Teacher theTeacher);

    public void deleteById(int theId);
}
