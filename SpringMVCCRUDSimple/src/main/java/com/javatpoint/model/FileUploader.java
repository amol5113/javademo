package com.javatpoint.model;

import java.util.Arrays;

public class FileUploader {

	private int id;  
	private String filename; 
	
	private byte[] filedata;
	private String fileContentType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	@Override
	public String toString() {
		return "FileUploader [id=" + id + ", filename=" + filename + ", filedata=" + Arrays.toString(filedata)
				+ ", fileContentType=" + fileContentType + "]";
	}




}