package com.website.slaven.stajic.ednevnik.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classes")
public class classes {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int class_id;

    @Column(name = "class_name")
    @NotEmpty(message = "Class name cannot be empty")
    private String class_name;



}
