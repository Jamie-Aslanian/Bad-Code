package Excell_converter.todatabase.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Excell_converter.todatabase.model.StoredProperties;

public class ExcelReader {

	private static Path path= Paths.get("");
	public List<StoredProperties> read()
	{
		List<StoredProperties> prop = new ArrayList<>();
        String csvFile = getpath()+"/csv/data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] input = line.split(cvsSplitBy);
                
                int f1 = Integer.parseInt(input[0]);
                int f2 = Integer.parseInt(input[1]);
                StoredProperties PR = new StoredProperties(f1, f2);
                prop.add(PR);
                //send to dao

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    return prop;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
		return null;

	}
	public static String getpath() 
	{
	    try{
	        String executionPath = System.getProperty("user.dir");
	        return executionPath.replace("\\", "/");
	      }catch (Exception e){
	        System.out.println("Exception caught ="+e.getMessage());
	      }
		return null;
	}
	
}
