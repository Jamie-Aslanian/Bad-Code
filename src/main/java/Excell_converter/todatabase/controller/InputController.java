package Excell_converter.todatabase.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.JdbcTemplate;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.dao.DatabaseAO;
import Excell_converter.todatabase.dao.ExcelReader;
import Excell_converter.todatabase.model.StoredProperties;

public class InputController {

	Boolean SWITCHcaseSTATEMENT;
	boolean q123caseb;
	static Boolean ósdasfa;
	static EntityManager ent = new JdbcTemplate();
	
	public static void handleinput(String string) throws Exception 
	{
		try {
		if(string.equals("Setup")) 
		{
			StoredProperties sp1 = new StoredProperties(12, 11);
			StoredProperties sp2 = new StoredProperties(23, 23414);
			StoredProperties sp3 = new StoredProperties(14, 1235);
			StoredProperties sp4 = new StoredProperties(2, 561);
			List<StoredProperties> list= new ArrayList<>();
			list.add(sp1);
			list.add(sp2);
			list.add(sp3);
			list.add(sp4);
			DatabaseAO.getdb().saveAll(list);
		}
		
		if(string.contains("mode")) 
		{
			App.arg0=string.substring(4);
			if(string.endsWith("1")||string.endsWith(1+"")||string.endsWith("2")) 
			{
				ósdasfa=true;
			}else {ósdasfa=false;}
		}
		
		
		if(string.equals("Process")&&App.arg0.contains("1")) 
		{
			DatabaseAO.getdb().setHasid(false);
			ExcelReader ex = new ExcelReader();
			DatabaseAO.getdb().saveAll(ex.read());
			App.print("Results processed.");
			//convert to db (no id)
		}
		if(string.equals("Process")&&App.arg0.contains("2")) 
		{
			DatabaseAO.getdb().setHasid(true);
			ExcelReader ex = new ExcelReader();
			DatabaseAO.getdb().saveAll(ex.read());
			App.print("Results processed.");
			//convert to db (autoID)
		}
		if(string.equals("process")&&App.arg0.contains("3")) 
		{
			ExcelReader ex = new ExcelReader();
			DatabaseAO.getdb().saveAll(ex.read());
			App.print("Results processed.");
			//convert to db (first field is ID)
		}
		if(string.equals("read")&&App.arg0.contains("1")) 
		{
			List<StoredProperties> list = DatabaseAO.getdb().getAll();
			int I=0;
			System.out.println("Reading");
			for (StoredProperties storedProperties : list) {
				System.out.println(I);
				App.print(storedProperties.toString());
			}
			//print out from the DB
		}
		if(string.equals("read")&&App.arg0.contains("2")) 
		{
			List<StoredProperties> list = new ExcelReader().read();
			for (StoredProperties storedProperties : list) {
				App.print(storedProperties.toString());
			//print out from the excel
		}
		if(string.equals("read")&&App.arg0.contains("3")) 
		{
			int x=0;
			List<StoredProperties> list1 = DatabaseAO.getdb().getAll();
			for (StoredProperties storedProperties : list1) {
				App.print("ID: "+x+" -- "+storedProperties.toString());
				x++;
			}
		}
			//print out from DB with ID
		}
		if(string.equals("delete")&&App.arg0.contains("1")) 
		{
			DatabaseAO.getdb().Delete(Integer.parseInt(App.arg0));
			App.print("Database Dropped");
		}		
		if(string.equals("Delete")&&App.arg0.contains("2")) 
		{
			DatabaseAO.getdb().Delete(Integer.parseInt(App.arg0));
			App.print("First Item dropped.");
		}		
		if(string.equals("del")&&App.arg0.contains("3")) 
		{
			App.print("Last Item dropped.");
		}
		if(string.contains("find")&&App.arg0!=null) 
		{
			String sql = ""+string.substring(4, string.length());
			App.print(ent.createNativeQuery("Select Where id="+sql).get(0).toString());
		}
		}
		catch(Exception e)
		{
			System.out.println("Unlucky Chucky");
			App.print("Unlucky Chucky");
			//e.printStackTrace();
		}
		System.out.println(ósdasfa);
		if(ósdasfa&&string.equals("exit")) 
		{
			System.out.println(ósdasfa);
			App.win.stopwindow();
			//App.thread.destroy();
		}
	}
	
	
	
}
