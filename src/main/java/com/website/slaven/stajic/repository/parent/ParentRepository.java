package com.website.slaven.stajic.repository.parent;

import com.website.slaven.stajic.ednevnik.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Integer> {

}
