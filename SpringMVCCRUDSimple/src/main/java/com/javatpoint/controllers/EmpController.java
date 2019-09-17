package com.javatpoint.controllers;   
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javatpoint.model.Emp;
import com.javatpoint.service.IEmpService;
import com.javatpoint.validator.EmployeeValidator;  
@Controller  
public class EmpController {  
	@Autowired  
	IEmpService service;//will inject service from xml file  

	@Autowired
	EmployeeValidator empValidate;
	
	@Autowired
	public MessageSource msgsource;
	

	@InitBinder
	protected void initBinder(WebDataBinder binder)  {
		binder.addValidators(empValidate);

	}

	
	
	@RequestMapping("/empform")  
	public String showform(Model m){ 
		m.addAttribute("emp", new Emp());
		return "empform"; 
	}  

	@RequestMapping(value="/save",method = RequestMethod.POST)  
	public String save(@ModelAttribute("emp") @Validated Emp emp, BindingResult br){
		
		
		System.out.println(emp);
		if(br.hasErrors())
		{
			return "empform";
		}
		else {
			service.save(emp);  
			return "redirect:/empform";
		}
	}  
	
	
	private static final String UPLOAD_DIRECTORY ="/images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	
	@RequestMapping("upload")
	public ModelAndView uploadForm(){
		return new ModelAndView("upload");	
	}
	
	@RequestMapping(value="savefile",method=RequestMethod.POST)
	public ModelAndView saveImage( @RequestParam CommonsMultipartFile file,HttpSession session) throws Exception
	{
		
	DiskFileItemFactory factory = new DiskFileItemFactory();
	factory.setSizeThreshold(THRESHOLD_SIZE);
	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	ServletFileUpload upload = new ServletFileUpload(factory);
	ServletContext context = session.getServletContext();

	String uploadPath = context.getRealPath(UPLOAD_DIRECTORY);
	System.out.println(uploadPath);	    

	byte[] bytes = file.getBytes();
	BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + file.getOriginalFilename())));
	stream.write(bytes);
	stream.flush();
	stream.close();
	     
	return new ModelAndView("upload","filesuccess","File successfully saved!");
	}
	
	
	@RequestMapping("/viewemp")  
	public String viewemp(Model m){  
		List<Emp> list=service.getEmployees();  
		m.addAttribute("list",list);
		return "viewemp";  
	}  

	@RequestMapping(value="/editemp/{id}")  
	public String edit(@PathVariable int id, Model m){  
		Emp emp=service.getEmpById(id);  
		m.addAttribute("command",emp);
		return "empeditform";  
	}  

	@RequestMapping(value="/editsave",method = RequestMethod.POST)  
	public String editsave(@ModelAttribute("emp") Emp emp){  
		service.update(emp);  
		return "redirect:/viewemp";  
	}   
	@RequestMapping(value="/deleteemp/{username}",method = RequestMethod.GET)  
	public String delete(@PathVariable String username){  
		service.delete(username);  
		return "redirect:/viewemp";  
	}   
	
	@RequestMapping("/datatableview")  
	public String dataTable(Model m) throws JsonGenerationException, JsonMappingException, IOException{  
		List<Emp> list=service.getEmployees(); 
		ObjectMapper mapper = new ObjectMapper();
		m.addAttribute("list",mapper.writeValueAsString(service.getEmployees()));
		return "DataTableView";  
	}  
	
	
	
	
	
	
}  