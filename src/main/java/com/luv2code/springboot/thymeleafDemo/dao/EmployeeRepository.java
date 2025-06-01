package com.luv2code.springboot.thymeleafDemo.dao;

import com.luv2code.springboot.thymeleafDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //sort by last name

    public List<Employee> findAllByOrderByLastNameAsc();
}
