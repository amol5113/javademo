package com.javatpoint.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javatpoint.model.Emp;

public class EmpDaoImpl implements IEmpDao {
	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Emp p) {

		String q = "INSERT INTO user2(username,password,email,usertype,gender,java,c,cpp,sql,comment) VALUES('"
				+ p.getUserName() + "','" + p.getPassword() + "','" + p.getEmail() + "','" + p.getUserType() + "','"
				+ p.getGender() + "','" + p.getJava() + "','" + p.getC() + "','" + p.getCpp() + "','" + p.getSql()
				+ "','" + p.getComment() + "')";

		System.out.println(p.getUserName() + p.getPassword() + p.getEmail() + p.getGender() + p.getJava());

		return template.update(q);
	}

	/*
	 * public int update(Emp p){ String
	 * sql="update Employee set name='"+p.getName()+"',accountno="+p.getAccountno()+
	 * ",email="+p.getEmail()+",ifsccode="+p.getIfsccode()+",dob="+p.getDob()+
	 * ",intrest="+p.getInterest()+", salary="+p.getSalary()+",designation='"+p.
	 * getDesignation()+"' where id="+p.getId()+""; return template.update(sql); }
	 */
	public int delete(String userName) {
		
		String sql = "delete from user2 where email='"+ userName +"'";
		return template.update(sql);
	}

	public Emp getEmpById(int id) {
		
		String sql = "select * from Employee where id=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Emp>(Emp.class));
	}

	public int update(Emp p) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	  public List<Emp> getEmployees(){ return
	  template.query("select * from user2",new RowMapper<Emp>(){ public Emp
	  mapRow(ResultSet rs, int row) throws SQLException { Emp e=new Emp();
	  
	  e.setUserName(rs.getString(1)); e.setPassword(rs.getString(2));
	  e.setEmail(rs.getString(3)); e.setUserType(rs.getString(4));
	  e.setGender(rs.getString(5)); e.setJava(rs.getString(6));
	  e.setC(rs.getString(7)); e.setCpp(rs.getString(8));
	  e.setSql(rs.getString(9)); e.setComment(rs.getString(10));
	  
	  return e; }
	  }); }
	 
}