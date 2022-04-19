package com.website.slaven.stajic.ednevnik.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class students {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must not contain special characters or numbers")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters or numbers")
    private String last_name;

    @Column(name = "age")
    private int age;

    @Column(name = "classes_class_id")
    private int classes_class_id;


}
