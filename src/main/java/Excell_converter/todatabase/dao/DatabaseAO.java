package Excell_converter.todatabase.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.model.StoredProperties;

public class DatabaseAO {
	private static final Logger LOGGER = Logger.getLogger(DatabaseAO.class.getName());
	private static String path = ExcelReader.getpath();
	private static DatabaseAO db;
	private static List<StoredProperties> data;
	private boolean hasid;
	private static String databaseLocation = path + "/DB/Database.ser";

	private DatabaseAO() {

	}

	public void saveAll() {
		try (FileOutputStream fileOut = new FileOutputStream(databaseLocation);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
			out.writeObject(data);
			App.print("File saved");
		} catch (IOException i) {
			LOGGER.severe(i.getMessage());
		}

	}

	public void saveAll(List<StoredProperties> e) {
		try (FileOutputStream fileOut = new FileOutputStream(databaseLocation);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
			out.writeObject(e);
			App.print("File saved");
		} catch (IOException i) {
			LOGGER.severe(i.getMessage());
		}

	}

	public static List<StoredProperties> getAll() {
		List<StoredProperties> e;
		try (FileInputStream fileIn = new FileInputStream(databaseLocation);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			e = (List<StoredProperties>) in.readObject();
		} catch (IOException i) {
			LOGGER.severe(i.getMessage());
			return Collections.emptyList();
		} catch (ClassNotFoundException c) {
			LOGGER.info("All loaded");
			LOGGER.severe(c.getMessage());
			return Collections.emptyList();
		}
		data = e;
		return data;

	}

	public StoredProperties findById(long id) {
		if (hasid) {
			return data.get((int) id);
		} else
			return null;
	}

	public void delete(int i) {
		if (i == 1) {
			if (i == 1) {
				int x = data.size();
				for (int j = x - 1; j >= 0; j--) {
					data.remove(j);
				}
			}
		} else {
			if (i == 2)
				data.remove(0);
			if (i == 3) {
				data.remove(data.size() - 1);
			}
		}
		this.saveAll();
	}

	public static DatabaseAO getdb() {

		if (db == null) {
			db = new DatabaseAO();
			return db;
		} else
			return db;
	}

	public boolean isHasid() {
		return hasid;
	}

	public void setHasid(boolean hasid) {
		this.hasid = hasid;
	}

}
