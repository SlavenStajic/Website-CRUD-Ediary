package com.website.slaven.stajic.repository.students;

import com.website.slaven.stajic.ednevnik.model.students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends JpaRepository<students, Integer> {

}
