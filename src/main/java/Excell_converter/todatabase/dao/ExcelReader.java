package Excell_converter.todatabase.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import Excell_converter.todatabase.model.StoredProperties;

public class ExcelReader {
	private static final Logger LOGGER = Logger.getLogger( ExcelReader.class.getName() );
	public List<StoredProperties> read()
	{
		List<StoredProperties> prop = new ArrayList<>();
        String csvFile = getpath()+"/csv/data.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (
        		BufferedReader br = new BufferedReader(new FileReader(csvFile));
        	){
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] input = line.split(cvsSplitBy);
                
                int f1 = Integer.parseInt(input[0]);
                int f2 = Integer.parseInt(input[1]);
                StoredProperties pr = new StoredProperties(f1, f2);
                prop.add(pr);
                //send to dao

            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
		return prop;

	}
	public static String getpath() 
	{
	    try{
	        String executionPath = System.getProperty("user.dir");
	        return executionPath.replace("\\", "/");
	      }catch (Exception e){
	        LOGGER.severe("Exception caught ="+e.getMessage());
	      }
		return null;
	}
	
}
