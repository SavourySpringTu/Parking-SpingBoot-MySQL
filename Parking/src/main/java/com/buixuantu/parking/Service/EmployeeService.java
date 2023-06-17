package com.buixuantu.parking.Service;

import java.util.List;

import com.buixuantu.parking.entity.EmployeeEntity;

public interface EmployeeService {
	List<EmployeeEntity> getAllEmployee(); 
	EmployeeEntity findEmployeeById(String id);
	EmployeeEntity login(String u, String p);
	EmployeeEntity addEmployee(String id,String name,String password,String role);
	EmployeeEntity updateEmployeeById(String id,String name,String password,String role);
	void deleteEmployeeById(String id);
}
