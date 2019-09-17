package com.javatpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javatpoint.dao.IEmpDao;
import com.javatpoint.model.Emp;

@Service("bank_service")
@Transactional
public class EmpServiceImpl implements IEmpService {
	
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  

	@Autowired
	IEmpDao dao;

	public int save(Emp p) {
		
		return dao.save(p);
	}

	public int update(Emp p) {
		// TODO Auto-generated method stub
		return dao.update(p);
	}

	public int delete(String userName) {
		// TODO Auto-generated method stub
		return dao.delete(userName);
	}

	public Emp getEmpById(int id) {
		// TODO Auto-generated method stub
		return dao.getEmpById(id);
	}

	public List<Emp> getEmployees() {
		// TODO Auto-generated method stub
		return dao.getEmployees();
	}

}
