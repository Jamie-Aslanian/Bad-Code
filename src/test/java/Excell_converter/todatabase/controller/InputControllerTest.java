package Excell_converter.todatabase.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.dao.DatabaseAO;
import Excell_converter.todatabase.dao.ExcelReader;
import Excell_converter.todatabase.model.StoredProperties;

public class InputControllerTest {
	
	private static DatabaseAO mockedDB = mock(DatabaseAO.class);
	private static ExcelReader mockedEX = mock(ExcelReader.class);
	
	@Before
	public void setupTests() {
		
		App.setTest(true);
		DatabaseAO.setdb(mockedDB);
		InputController.setExcellReader(mockedEX);
		when(mockedDB.isHasid()).thenReturn(true);
		when(mockedDB.findById(1)).thenReturn(new StoredProperties(1,10));
		when(mockedDB.getAll()).thenReturn(Arrays.asList(new StoredProperties(1,10)));
		//when(DatabaseAO.getdb()).thenReturn(mockedDB);
		
	}
	
	@Test
	public void handleFindTest() {
		InputController.handleinput("mode1");
		InputController.handleinput("find 1");
		verify(mockedDB).findById(1);
	}
	
	@Test
	public void handleModeTest() {
		
		InputController.handleinput("mode1");
		assertTrue(App.getArg0().equals("1"));
		InputController.handleinput("mode2");
		assertTrue(App.getArg0().equals("2"));
		InputController.handleinput("mode3");
		assertTrue(App.getArg0().equals("3"));
	}
	
	@Test
	public void handleDeleteTest() {
		InputController.handleinput("mode1");
		InputController.handleinput("delete");
		verify(mockedDB).delete(1);
		InputController.handleinput("mode2");
		InputController.handleinput("delete");
		verify(mockedDB).delete(2);
		InputController.handleinput("mode3");
		InputController.handleinput("delete");
		verify(mockedDB).delete(1);
		
	}
	

	@Test
	public void handleInvalidCommand() {
		InputController.handleinput("Unluckychucky");
	}
	
	@Test
	public void handleReadTest() {

		InputController.handleinput("mode1");
		InputController.handleinput("read");
		verify(mockedDB).getAll();
		InputController.handleinput("mode2");
		InputController.handleinput("read");
		verify(mockedEX, times(1)).read();
		InputController.handleinput("mode3");
		InputController.handleinput("read");
		verify(mockedDB, times(2)).getAll();
		
	}
	
	@Test
	public void handleProcessTest() 
	{
		InputController.handleinput("mode1");
		InputController.handleinput("process");
		verify(mockedDB).saveAll(mockedEX.read());
		InputController.handleinput("mode2");
		InputController.handleinput("process");
		verify(mockedDB, times(2)).saveAll(mockedEX.read());
		InputController.handleinput("mode3");
		InputController.handleinput("process");
		verify(mockedDB, times(3)).saveAll(mockedEX.read());
	}
}
