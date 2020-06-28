package com.hms.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	
	
	//initialization
	Properties prop;
	FileInputStream file;
	public String path;
	
	//constructor
	public PropertyFileReader(String path) throws FileNotFoundException {

		prop = new Properties();
		this.path = path;
		file = new FileInputStream(path);
	}
	
	
	//Reading data from property file
	public String getKeyValue(String key) throws IOException {
		
		prop.load(file);
		String result = prop.getProperty( key);
		return result;
		
	}
	
	//Writing data to property file
	public void setKeyValue(String key, String value) throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/Data.properties");
		prop.setProperty(key, value);
		prop.store(fileOut, null);
			
	}
	
	

	
	
	
	


}
