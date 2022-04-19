package com.website.slaven.stajic.repository.classes;


import com.website.slaven.stajic.ednevnik.model.classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<classes,Integer> {


}
