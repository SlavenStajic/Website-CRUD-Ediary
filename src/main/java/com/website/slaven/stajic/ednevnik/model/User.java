package com.website.slaven.stajic.ednevnik.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 4,max = 15 , message = "user name cant have less then 4. character or more then 15")
    @Column(name = "userName")
    private String userName;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 4 , message = "password cant have less then 4. characters ")
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;
    @Column(name = "status")
    private String status;


}
