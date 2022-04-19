package com.website.slaven.stajic.CRUDapp.service;

import com.website.slaven.stajic.CRUDapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

        public List<Employee> findAll();

        public Employee findById(int theId);

        public void save(Employee theEmployee);

        public void deleteById(int theId);

        public List<Employee> searchBy(String theName);

    }
