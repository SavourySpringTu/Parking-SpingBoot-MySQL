package com.buixuantu.Service;

import java.util.List;

import com.buixuantu.entity.EmployeeEntity;

public interface EmployeeService {
	List<EmployeeEntity> getAllEmployee(); 
	EmployeeEntity findEmployeeById(String id);
	EmployeeEntity login(String u, String p);
	EmployeeEntity addEmployee(String id,String name,String password);
	EmployeeEntity updateEmployeeById(String id,String name,String password);
	void deleteEmployeeById(String id);
}
