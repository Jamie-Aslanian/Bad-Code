package Excell_converter.todatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.JdbcTemplate;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.dao.DatabaseAO;
import Excell_converter.todatabase.dao.ExcelReader;
import Excell_converter.todatabase.model.StoredProperties;

public class InputController {
	private static final Logger LOGGER = Logger.getLogger( InputController.class.getName() );
	
	static Boolean someBoolean;
	static EntityManager ent = new JdbcTemplate();
	static DatabaseAO db = DatabaseAO.getdb();
	static ExcelReader ex = new ExcelReader();

	private static void handleDelete() {
		switch(App.getArg0()) {
		case "1":
			DatabaseAO.getdb().delete(Integer.parseInt(App.getArg0()));
			App.print("Database Dropped");
			break;
		case "2":
			DatabaseAO.getdb().delete(Integer.parseInt(App.getArg0()));
			App.print("First Item dropped.");
			break;
		case "3":
			App.print("Last Item dropped.");
			break;
		default:
			handleInvalidMode();
			break;
		}
	}
	
	private static void handleExit() {
		App.closeApplication();
	}
	
	private static void handleFind(String string) {
		String sql = "" + string.substring(4, string.length());
		App.print(ent.createNativeQuery("select Where id=" + sql).get(0).toString());
	}
	
	private static void handleHelpRequest() {
		App.print("Valid commands;");
		App.print("Setup // <Mode1, Mode2, Mode3> // Process // Read // Delete // Find // <Close, End, Exit, Quit>");
	}

	public static synchronized void handleinput(String string){
		switch (string.toLowerCase().split(" ")[0]) {
		case "setup":
			handleSetup();
			break;

		case "mode1":
		case "mode2":
		case "mode3":
			handleMode(string.toLowerCase());
			break;

		case "process":
			handleProcess();
			break;

		case "read":
			handleRead();
			break;

		case "delete":
			handleDelete();
			break;

		case "find":
			handleFind(string);
			break;

		case "/help":
			handleHelpRequest();
			break;
			
		case "exit":
		case "close":
		case "end":
		case "quit":
			handleExit();
			break;
			
		default:
			handleInvalidCommand(string);
			break;
		}
	}

	private static void handleInvalidCommand(String string) {
		String logInfo = "Invalid command <" + string + "> entered";
		LOGGER.info(logInfo);
		App.print("Command <" + string +"> not recognised");
		App.print("Use /help for a list of valid commands");
	}

	private static void handleInvalidMode() {
		LOGGER.severe("Invalid Mode has been reached");
		App.print("Invalid Mode has been reached");
	}

	private static void handleMode(String mode) {
		if (mode.equals("mode1")) {
			App.setArg0("1");
			LOGGER.info("Entered Mode 1");
			App.print("Entered Mode 1");
		} else if(mode.equals("mode2")) {
			App.setArg0("2");
			LOGGER.info("Entered Mode 2");
			App.print("Entered Mode 2");
		} else if(mode.equals("mode3")) {
			App.setArg0("3");
			LOGGER.info("Entered Mode 3");
			App.print("Entered Mode 3");
		} else {
			handleInvalidMode();
		}
		someBoolean = true;
	}

	private static void handleProcess() {
		Boolean success = false;
		switch (App.getArg0()) {
		case "1":
			DatabaseAO.getdb().setHasid(false);
			
			DatabaseAO.getdb().saveAll(ex.read());
			success = true;
			// convert to db (no id)
			break;

		case "2":
			DatabaseAO.getdb().setHasid(true);
			ex = new ExcelReader();
			DatabaseAO.getdb().saveAll(ex.read());
			success = true;
			// convert to db (autoID)
			break;

		case "3":
			ex = new ExcelReader();
			DatabaseAO.getdb().saveAll(ex.read());
			success = true;
			// convert to db (first field is ID)
			break;

		default:
			handleInvalidMode();
			break;
		}
		if(success) {
			App.print("Results processed.");
		}
	}
	
	private static void handleRead() {
		switch (App.getArg0()) {
		case "1":
			List<StoredProperties> list = db.getAll();
			LOGGER.info("Reading");
			for (StoredProperties storedProperties : list) {
				App.print(storedProperties.toString());
			}
			// print out from the DB
			break;
		case "2":
			List<StoredProperties> propList = ex.read();
			for (StoredProperties storedProperties : propList) {
				App.print(storedProperties.toString());
				// print out from the excel
			}
			break;
		case "3":
			int x = 0;
			List<StoredProperties> list1 = db.getAll();
			for (StoredProperties storedProperties : list1) {
				App.print("ID: " + x + " -- " + storedProperties.toString());
				x++;
			}
			break;
		default:
			handleInvalidMode();
			break;
		}
	}
	
	private static void handleSetup() {
		StoredProperties sp1 = new StoredProperties(12, 11);
		StoredProperties sp2 = new StoredProperties(23, 23414);
		StoredProperties sp3 = new StoredProperties(14, 1235);
		StoredProperties sp4 = new StoredProperties(2, 561);
		List<StoredProperties> list = new ArrayList<>();
		list.add(sp1);
		list.add(sp2);
		list.add(sp3);
		list.add(sp4);
		DatabaseAO.getdb().saveAll(list);
		
	}
	
	private InputController() {}
	
	public static void setExcellReader(ExcelReader e) {
		if (App.isTest()) ex=e;
	}
	
	public static void resetDB() {
		if(App.isTest()==true) {
		db=DatabaseAO.getdb();
		}
	}

}
