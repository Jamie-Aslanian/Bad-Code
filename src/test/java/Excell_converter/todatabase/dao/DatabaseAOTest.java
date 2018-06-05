package Excell_converter.todatabase.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Test;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.controller.InputController;
import Excell_converter.todatabase.model.StoredProperties;

public class DatabaseAOTest {
	private static final Logger LOGGER = Logger.getLogger( DatabaseAOTest.class.getName() );
	static StoredProperties element2 =new StoredProperties(11, 103);
	
	private static List<StoredProperties> testlist = Arrays.asList(new StoredProperties(10, 10)
			,element2
			,new StoredProperties(12, 142)
			,new StoredProperties(13, 12));
	
	@Test
	public void testall() {
		ArrayList<StoredProperties> testlist = new ArrayList<>();
		for (StoredProperties storedProperties : this.testlist) {
			testlist.add(storedProperties);
		}
		DatabaseAO db = DatabaseAO.getdb();
		App.setTest(true);
		db.saveAll(testlist);
		LOGGER.severe(""+db.getAll().get(0).getField1()+" "+db.getAll().get(0).getField2());
		assertTrue(db.getAll().get(0).getField2()==10);
		db.delete(1);
		LOGGER.severe(""+db.getAll().size());
		assertTrue(db.getAll().isEmpty());
		db.setHasid(true);
		assertTrue(db.isHasid());
		db.saveAll(testlist);
		db.getAll();
		assertTrue(db.findById(1).getField2()==103);
		db.delete(2);
		assertTrue(db.getAll().size()==3);
		db.delete(3);
		assertTrue(db.getAll().size()==2);
	}
	@Test
	public void setupTest() {
		InputController.handleinput("Setup");
		DatabaseAO db = DatabaseAO.getdb();
		assertThat(db.getAll().size(), is(4));
		DatabaseAO.setdb(null);
	}
	
}
