package Excell_converter.todatabase.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Excell_converter.todatabase.model.StoredProperties;



public class DatabaseAO {

	private String password="strongpasswordthatnoonewillguess";
	private String User="root";
	private static String path= ExcelReader.getpath();
	private static DatabaseAO db;
	private static List<StoredProperties> data;
	private boolean hasid;
	private DatabaseAO() 
	{
		
	}
	
	   public void saveAll() 
	   {
		   try {
		         FileOutputStream fileOut =
		         new FileOutputStream(path+"/DB/Database.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(data);
		         out.close();
		         System.out.printf("File saved");
		      } catch (IOException i) {
		         i.printStackTrace();
		      }

	   }
	   public void saveAll(List<StoredProperties> e) 
	   {
		   try {
		         FileOutputStream fileOut =
		         new FileOutputStream(path+"/DB/Database.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(e);
		         out.close();
		         System.out.printf("File saved");
		      } catch (IOException i) {
		         i.printStackTrace();
		      }

	   }
	
	
	   public List<StoredProperties> getAll() {
		    List<StoredProperties> e;
		      try {
		         FileInputStream fileIn = new FileInputStream(path+"/DB/Database.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         e = (List<StoredProperties>) in.readObject();
		         in.close();

		      } catch (IOException i) {
		         i.printStackTrace();
		         return null;
		      } catch (ClassNotFoundException c) {
		         System.out.println("All loaded");
		         c.printStackTrace();
		         return null;
		      }
			data=e;
			return data;
		      
		   }



	public StoredProperties findById(long id) throws Exception {
		if(hasid) 
		{
			return data.get((int) id);
		}else {
		throw new Exception("Get out of my swamp");
		}
	}

	public void Delete(int I) 
	{
		if(I==1) 
		{
			if(I==1) 
			{
				int x = data.size();
				for(int i=x-1;i>=0;i--) {
				data.remove(i);}
			}
		}
		else 
		{
			if(I==2) data.remove(0);
			if(I==3) 
			{
				data.remove(data.size()-1);
			}
		}
		this.saveAll();
	}


	public static DatabaseAO getdb() {
		
		if(db==null) 
		{
			db = new DatabaseAO();
			return db;
		}
		else return db;
	}

	public boolean isHasid() {
		return hasid;
	}

	public void setHasid(boolean hasid) {
		this.hasid = hasid;
	}
	
		
	}
	
	

