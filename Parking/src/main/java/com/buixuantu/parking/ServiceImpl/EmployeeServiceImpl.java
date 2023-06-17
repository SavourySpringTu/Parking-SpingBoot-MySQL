package com.buixuantu.parking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.parking.Repository.EmployeeRepository;
import com.buixuantu.parking.Service.EmployeeService;
import com.buixuantu.parking.Service.RoleService;
import com.buixuantu.parking.entity.EmployeeEntity;
import com.buixuantu.parking.entity.RoleEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RoleService roleSerice;
	
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
	public EmployeeEntity addEmployee(String id,String name, String password,String role) {
		RoleEntity r = roleSerice.findRoleById(role);
		EmployeeEntity tmp = new EmployeeEntity(id,name,password,r);
		return employeeRepository.save(tmp);
	}
	
	@Override
	public EmployeeEntity updateEmployeeById(String id,String role,String name, String password) {
		EmployeeEntity tmp = employeeRepository.findById(id).orElse(null);
		tmp.setName(name);
		tmp.setPassword(password);
		tmp.setRole(roleSerice.findRoleById(role));
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
