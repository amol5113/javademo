package com.javatpoint.controllers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javatpoint.dao.EmpDaoImpl;
import com.javatpoint.model.Emp;
import com.javatpoint.model.FileUploader;
import com.javatpoint.model.SimpleImage;
import com.javatpoint.model.UploadedFile;
import com.javatpoint.service.IEmpService;
import com.javatpoint.validator.EmployeeValidator;

@Controller
public class EmpController {
	@Autowired
	IEmpService service;// will inject service from xml file

	@Autowired
	EmployeeValidator empValidate;

	@Autowired
	EmpDaoImpl dao;

	@Autowired
	public MessageSource msgsource;

	@Value("${upload_image_path}")
	private String imagePath;

	@Value("${image_path}")
	private String imageret;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(empValidate);

	}

	// to employee form
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("emp", new Emp());
		return "empform";
	}

	// save data
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") @Validated Emp emp, HttpSession session, BindingResult br) {
		String path = session.getServletContext().getRealPath("/");
		// String filename=file.getOriginalFilename();

		// System.out.println(path+" "+filename);

		System.out.println(emp);
		if (br.hasErrors()) {
			return "empform";
		} else {
			service.save(emp);
			return "redirect:/empform";
		}
	}

	private static final String UPLOAD_DIRECTORY = "D:/Demo/SpringMVCCRUDSimple/src/main/webapp/resources/images/silde1.jpg";

	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB

	// upload from
	@RequestMapping("/upload")
	public String uploadForm(ModelMap map) {
		// map.put("uploadFile",new UploadedFile());
		return "upload";
	}

	// save file
	@RequestMapping(value = "savefile", method = RequestMethod.POST)
	public ModelAndView saveImage(@RequestParam CommonsMultipartFile file, @RequestParam("name") String name,
			HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		ServletContext context = session.getServletContext();

		String uploadPath = context.getRealPath(UPLOAD_DIRECTORY);
		System.out.println("dfgd" + uploadPath);

		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(uploadPath + File.separator + file.getOriginalFilename())));
		stream.write(bytes);
		// DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		System.out.println(dir);
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
		System.out.println("file server" + serverFile);
		dao.addImage(name, stream, serverFile);
		stream.flush();
		stream.close();
		return new ModelAndView("upload", "filesuccess", "File successfully saved!");
	}

	// view User
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list = service.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	// edit employee
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = service.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	// save User
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Emp emp) {
		service.update(emp);
		return "redirect:/viewemp";
	}

	// delete user
	@RequestMapping(value = "/deleteemp/{username}", method = RequestMethod.GET)
	public String delete(@PathVariable String username) {
		service.delete(username);
		return "redirect:/viewemp";
	}

	// To data table
	@RequestMapping(value = "/datatableview", method = RequestMethod.GET)
	public String dataTable(Model m) throws JsonGenerationException, JsonMappingException, IOException {
		List<Emp> list = service.getEmployees();
		ObjectMapper mapper = new ObjectMapper();
		m.addAttribute("list", mapper.writeValueAsString(list));
		return "viewdatatablepage";
	}

	// get list of images under the web app
	@RequestMapping(value = "/list_images")
	public String listImages(Model map, HttpServletRequest request) {
		String imagePath1 = "D:\\uploaded_contents";
		System.out.println("in list images " + imagePath1);
		File uploadLocationDir = new File(imagePath1);
		System.out.println(uploadLocationDir.exists() + " " + uploadLocationDir.isDirectory() + " "
				+ uploadLocationDir.getAbsolutePath());
		// new File(request.getServletContext().getRealPath("/images"));
		String[] files = uploadLocationDir.list();
		System.out.println("files " + Arrays.toString(files));
		map.addAttribute("image_list", files);
		// map.addAttribute("loc", imagePath1);
		return "list_images";
	}

	// choose image
	@RequestMapping(value = "/choose")
	public String chooseImage(Model map, @RequestParam String imgName) {
		System.out.println("in choose img name " + imgName);
		System.out.println("in choose img name " + imageret);
		map.addAttribute("img_name", imgName);
		return "show_image";
	}

	final String filePath = "D:/Demo/SpringMVCCRUDSimple/src/main/webapp/resources/images/";
	final String downloadDirectory = "/StudentData/src/main/webapp/images/";

	// upload file in project directory
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(HttpServletRequest servletRequest, @RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				/* System.out.println(imagePath); */
				File dir = new File(filePath);
				if (!dir.exists())
					dir.mkdirs();
				System.out.println(dir);
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println("file server" + serverFile);
				// add image path into database
				dao.addImagePath(name, serverFile);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				dao.addImage(name, stream, serverFile);
				stream.close();
				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	// upload file outside the project(within drive)
	@RequestMapping(value = "/uploadImageDrive")
	public String saveDoctor(UploadedFile uploadedFile, SimpleImage simpleImage, ModelMap model) {

		InputStream inputStreamL = null;
		OutputStream outputStreamL = null;

		MultipartFile fileLicense = uploadedFile.getFileLicense();

		String fileNameLicense = fileLicense.getOriginalFilename();
		String pathImage = "D:\\Demo\\Images\\" + "image" + fileNameLicense;
		simpleImage.setSimpleImage(pathImage);

		try {
			inputStreamL = fileLicense.getInputStream();

			File newFileL = new File(pathImage);
			if (!newFileL.exists()) {
				newFileL.createNewFile();
			}

			outputStreamL = new FileOutputStream(newFileL);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStreamL.read(bytes)) != -1) {
				outputStreamL.write(bytes, 0, read);
			}
			outputStreamL.close();
			return "You successfully uploaded file=" + pathImage;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "index";

	}

	// view files
	@RequestMapping(value = "/viewFiles")
	public String viewFiles(Model model, HttpServletRequest request) {
		File uploadLocationDir = new File(filePath);
		String[] files = uploadLocationDir.list();
		System.out.println("files " + Arrays.toString(files));
		model.addAttribute("fileList", files);
		model.addAttribute("filePath", downloadDirectory);
		return "viewFiles";
	}

	// to saveImagePath page
	@RequestMapping(value = "/imagePathStore")
	public String viewImage(ModelMap map) {
		map.put("simpleImagee", new SimpleImage());
		map.put("uploadedFile", new UploadedFile());
		System.out.println("controller callled");
		// map.addAttribute("uploadedFile",new UploadedFile());
		return "saveImagePath";
	}

	public boolean supports(Class<?> clazz) {
		return SimpleImage.class.isAssignableFrom(clazz);
	}

	// from imagePathUpload
	@RequestMapping(value = "/imagePathUpload")
	public String uploadImagePath(UploadedFile uploadedFile, SimpleImage simpleImage, ModelMap model) {

		/*
		 * if(medService.doctorByEmailId(simpleImage.getSimpleImage())!=null) { return
		 * "saveImagePath"; }
		 */
		InputStream inputStreamL = null;
		OutputStream outputStreamL = null;

		MultipartFile fileLicense = uploadedFile.getFileLicense();

		String fileNameLicense = fileLicense.getOriginalFilename();
		String pathLicense = "D:\\Amol\\" + fileNameLicense;
		simpleImage.setSimpleImage(pathLicense);

		try {
			inputStreamL = fileLicense.getInputStream();

			File newFileL = new File(pathLicense);
			if (!newFileL.exists()) {
				newFileL.createNewFile();
			}

			outputStreamL = new FileOutputStream(newFileL);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStreamL.read(bytes)) != -1) {
				outputStreamL.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dao.insertDoctor(simpleImage);
		return "login";

	}

	// retrieve image from project directory static
	@RequestMapping(value = "/view_images")
	public String retrieveFileByPath(ModelMap model) throws IOException {

		try {

			// degree image
			File file = new File("D:/Demo/SpringMVCCRUDSimple/src/main/webapp/resources/images/silde1.jpg");
			FileInputStream fis;
			fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int b;
			byte[] buffer = new byte[1024];
			while ((b = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, b);
			}
			byte[] fileBytes = bos.toByteArray();
			fis.close();
			bos.close();
			byte[] encoded = Base64.getEncoder().encode(fileBytes);
			String encodedString = new String(encoded);

			model.put("image", encodedString);

			return "show_image";

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ModelMap map = new ModelMap();
		return null;
	}

	// retrieve file from database
	@RequestMapping(value = "/retrieveFileDB")
	public String retrieveFileFromDBBt(HttpServletResponse response, ModelMap model, @RequestParam("name") String name)
			throws IOException, SQLException {
		try {

			byte[] ph = dao.getPhotoByName(name);
			if (ph != null) {
				System.out.println("name=" + name);
				InputStream inputStream = new ByteArrayInputStream(ph);
				IOUtils.copy(inputStream, response.getOutputStream());
				
				 byte[] buffer = new byte[4096];
				 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				 int bytesRead = -1;
				 while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
				 
				 byte[] imageBytes = outputStream.toByteArray();
	             //   String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                byte[] encoded = Base64.getEncoder().encode(imageBytes);
	          	  String encodedString = new String(encoded);
	               // ((ServletContext) response).setAttribute("image", imageBytes);
	                model.put("image", encodedString);
	                inputStream.close();
	                outputStream.close();
	                
	                return "show_image";
	                //book.setBase64Image(base64Image);
				// degree image
				/*
				 * String name1=name; File file = new
				 * File("D:/Demo/SpringMVCCRUDSimple/src/main/webapp/resources/images/"+name1+""
				 * ); FileInputStream fis; fis = new FileInputStream(file);
				 * ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
				 *  */
				//	int b; //
				//	byte[] buffer = ph.getBytes(1, (int) ph.length()); while ((b = fis.read(ph)) != -1)
				 // { bos.write(ph, 0, b); } 
				//	byte[] fileBytes = bos.toByteArray(); 
					//fis.close();
				//  bos.close(); byte[] encoded = Base64.getEncoder().encode(fileBytes); String
				 // encodedString = new String(encoded);
				

				// model.put("image", encodedString);

			//	return "show_image";
			} else {
				return "The" + name + "imahe is not available please check IMAGE NAME";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ModelMap map = new ModelMap();
		return null;
	}

	// upload file to jsp page
	@RequestMapping(value = "/upload_ajax")
	public String fileUploadForm(Model model) {
		return "uploadImgThAjax";
	}

	// upload file AJAX from controller page
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

		// Save file on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File("D:/Amol/Image/", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {
			return new ResponseEntity<String>("Invalid file.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("File Uploaded Successfully.", HttpStatus.OK);
	}

	  //retrieve file from database with path
	  @RequestMapping(value = "/retrieveFile") 
	  public String retrieveFileFromDB(ModelMap model,@RequestParam("name") String name) throws IOException, SQLException {
	  
	  try {
	  
	  byte[] ph=dao.getPhotoByName(name);
	  if(ph!=null) 
	  	{
	  System.out.println("name="+name); //
	  InputStream inputStream = new ByteArrayInputStream(ph); //
	 // IOUtils.copy(inputStream,response.getOutputStream()); //
	   
	  String name1=name; 
	  File file = new File("D:/Demo/SpringMVCCRUDSimple/src/main/webapp/resources/images/"+name1+"");
	  FileInputStream fis; fis = new FileInputStream(file);
	  ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	  int b; //
	  //byte[] buffer = ph.getBytes(1, (int) ph.length()); 
	  while ((b = fis.read(ph)) != -1)
	  { 
		  bos.write(ph, 0, b);
	  } 
	  byte[] fileBytes = bos.toByteArray();
	  fis.close();
	  bos.close();
	  byte[] encoded = Base64.getEncoder().encode(fileBytes);
	  String encodedString = new String(encoded);
	  
	  model.put("image", encodedString);
	  
	  return "show_image"; 
	  } 
	  else 
	  { 
		  return "The" + name +
	  "imahe is not available please check IMAGE NAME"; 
	  } 
	  } catch(FileNotFoundException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); 
	  return "error";} // ModelMap map = new ModelMap(); return null; }
	  }
	  
	  
	  @RequestMapping(value = "/file_upload")
		public String uploadFile(){
			return "upload1";    
		}
	  
		//controller for saving the file in a particular location
		@RequestMapping(value="savefile1",method=RequestMethod.POST)
		public ModelAndView saveimage( @RequestParam CommonsMultipartFile file,HttpSession session) throws Exception
		{
			dao.uploadFilePS(file.getOriginalFilename(), file.getContentType(), file);
			return new ModelAndView("upload1","filesuccess","File successfully saved!");
		}
	  
		@RequestMapping(value = "/viewDatabaseFiles")
		public String viewStud(Model m){
			List<FileUploader> list= dao.viewFileList();
			m.addAttribute("list",list);  
			return "viewDBFiles";    
		}

		@RequestMapping(value="downloadFile/{id}")
		public void downloadFile(@PathVariable("id")int id, HttpServletResponse response) throws IOException {
			FileUploader fileUploader = dao.getDownloadableFile(id);
			//System.out.println("************************================");
			//System.out.println(fileUploader);
			
			response.setHeader("Content-Encoding", "UTF-8");
			response.setContentType("application/octet-stream");
			response.setContentType(fileUploader.getFileContentType());
			response.setContentLength(fileUploader.getFiledata().length);
			response.setHeader("Content-Disposition","attachment; filename=\"" + fileUploader.getFilename() +"\"");
			FileCopyUtils.copy(fileUploader.getFiledata(), response.getOutputStream());
			byte[] fileContent = fileUploader.getFiledata();
			response.getOutputStream().write(fileContent); 
		}
}