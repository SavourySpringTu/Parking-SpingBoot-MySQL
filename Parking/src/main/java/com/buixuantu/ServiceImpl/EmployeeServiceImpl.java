package com.buixuantu.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.Repository.EmployeeRepository;
import com.buixuantu.Service.EmployeeService;
import com.buixuantu.entity.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<EmployeeEntity> getAllEmployee() {
		return employeeRepository.findAll();
	}
	@Override
	public EmployeeEntity login(String u, String p) {
		List<EmployeeEntity> list= employeeRepository.findAll();
		for(EmployeeEntity a: list) {
			if(a.getId().equals(u)==true && a.getPassword().equals(p)==true) {
				return a;
			}
		}
		return null;
	}
	@Override
	public EmployeeEntity addEmployee(String id, String name, String password) {
		EmployeeEntity tmp = new EmployeeEntity(id,name,password);
		return employeeRepository.save(tmp);
	}
	@Override
	public EmployeeEntity updateEmployeeById(String id, String name, String password) {
		EmployeeEntity tmp = employeeRepository.findById(id).orElse(null);
		tmp.setName(name);
		tmp.setPassword(password);
		return employeeRepository.save(tmp);
	}
	@Override
	public void deleteEmployeeById(String id) {
		employeeRepository.deleteById(id);
	}
	@Override
	public EmployeeEntity findEmployeeById(String id) {
		return employeeRepository.findById(id).orElse(null);
	}
}
