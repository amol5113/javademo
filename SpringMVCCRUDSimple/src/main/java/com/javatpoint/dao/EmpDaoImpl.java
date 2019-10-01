package com.javatpoint.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.web.multipart.MultipartFile;

import com.javatpoint.model.Emp;
import com.javatpoint.model.FileUploader;
import com.javatpoint.model.SimpleImage;



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
		String sql = "delete from user2 where username='"+ userName +"'";
		return template.update(sql);
	}

	public Emp getEmpById(int id) {

		String sql = "select * from user2 where username=?";
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

	public int addImage(String name,BufferedOutputStream img,File path) {
		try {
			String q="insert into imagestore(name,image,imagepath) values('"+name+"','"+img+"','"+path+"')";
			/*
			 * template.update( "INSERT INTO user2 (image) VALUES (?)", new Object[] { new
			 * SqlLobValue(img) });
			 */
			//System.out.println(q);
			return template.update(q);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int addImagePath(String name,File path) {
		try {
			//String p=path;
			String q="insert into imagepath(name,imagepath) values('"+name+"','"+path+"')";
			/*
			 * template.update( "INSERT INTO user2 (image) VALUES (?)", new Object[] { new
			 * SqlLobValue(img) });
			 */
			//System.out.println(q);
			return template.update(q);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int insertDoctor(SimpleImage simpleImage) {
		try {
			String q="insert into imagepath(name,imagepath) values('"+simpleImage.getName()+"','"+simpleImage.getSimpleImage()+"')";
			
			return template.update(q);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;

	}
	
	public String retrieveImage(String name) {
		try {
			String q="SELECT image FROM imagestore WHERE name='"+name+"'";
			
			return template.queryForObject(
		                q, new Object[]{name}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public byte[] getPhotoByName(String name) {

		String query = "select image from imagestore where name='"+name+"'";
		byte[] photo = template.queryForObject(query, byte[].class);
		return photo;
	}
	
	
	public Boolean uploadFilePS(final String fileName, final String fileContentType, final MultipartFile file) {
		String query= "INSERT INTO filestore(filename, filecontenttype, filedata) VALUES (?,?,?)";  
		return template.execute(query,new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps)  
					throws SQLException, DataAccessException {  
				ps.setString(1, fileName);
				ps.setString(2, fileContentType);
				try {
					ps.setBinaryStream(3, file.getInputStream(), file.getBytes().length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ps.execute();  
			}
		}				
	);
		
	}
	
	
	public List<FileUploader> viewFileList() {
		return template.query("SELECT id, filename, filecontenttype, filedata FROM filestore",new RowMapper<FileUploader>(){    
			public FileUploader mapRow(ResultSet rs, int row) throws SQLException {    
				FileUploader fileUploader=new FileUploader();    
				fileUploader.setId(rs.getInt(1));
				fileUploader.setFilename(rs.getString(2));
				fileUploader.setFileContentType(rs.getString(3));
				fileUploader.setFiledata(rs.getBytes(4));
				return fileUploader;    
			}    
		});    
	}
	
	
	public FileUploader getDownloadableFile(int id) {
		String sql="select * from filestore where id=?";    
		return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<FileUploader>(FileUploader.class));  
	}
	
	
}