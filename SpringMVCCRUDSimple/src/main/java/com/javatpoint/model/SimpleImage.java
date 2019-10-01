package com.javatpoint.model;

public class SimpleImage {

	private String name;
	private String simpleImage;

	public String getSimpleImage() {
		return simpleImage;
	}

	public void setSimpleImage(String simpleImage) {
		this.simpleImage = simpleImage;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SimpleImage [simpleImage=" + simpleImage + "]";
	}

	
	
}
