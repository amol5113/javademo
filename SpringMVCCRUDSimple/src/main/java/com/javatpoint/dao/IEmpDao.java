package com.javatpoint.dao;

import java.util.List;

import com.javatpoint.model.Emp;

public interface IEmpDao {
	
	int save(Emp p);
	int update(Emp p);
	int delete(String userName);
	Emp getEmpById(int id);
	List<Emp> getEmployees();
	
	
	

}
