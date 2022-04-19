package com.website.slaven.stajic.ednevnik.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private int teacher_id;

    @Column(name = "first_name")
    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters or numbers")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters or numbers")
    private String last_name;


    @Column(name = "user_user_id")
    private int user_user_id;


    @Column(name = "classes_class_id")
    private int classes_class_id;



}
