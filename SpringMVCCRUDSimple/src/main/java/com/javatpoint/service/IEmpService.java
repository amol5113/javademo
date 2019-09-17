package com.javatpoint.service;

import java.util.List;

import com.javatpoint.model.Emp;

public interface IEmpService {
	
	int save(Emp p);
	int update(Emp p);
	int delete(String id);
	Emp getEmpById(int id);
	List<Emp> getEmployees();

}
