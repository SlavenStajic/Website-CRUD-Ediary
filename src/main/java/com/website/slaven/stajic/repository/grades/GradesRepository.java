package com.website.slaven.stajic.repository.grades;

import com.website.slaven.stajic.ednevnik.model.grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<grades,Integer> {
}
