package com.website.slaven.stajic.repository.Teacher;

import com.website.slaven.stajic.ednevnik.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
