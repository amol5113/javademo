package com.javatpoint.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadedFile {    
    
	 private MultipartFile fileLicense;
	
	public MultipartFile getFileLicense() {
		return fileLicense;
	}
	public void setFileLicense(MultipartFile fileLicense) {
		this.fileLicense = fileLicense;
	}
	    
	   
}    