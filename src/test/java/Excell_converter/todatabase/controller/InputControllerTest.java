package Excell_converter.todatabase.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.AppTest;
import Excell_converter.todatabase.dao.DatabaseAO;
import Excell_converter.todatabase.dao.ExcelReader;
import Excell_converter.todatabase.model.StoredProperties;
import Excell_converter.todatabase.view.Window;

public class InputControllerTest {
	
	private static DatabaseAO mockedDB = mock(DatabaseAO.class);
	private static ExcelReader mockedEX = mock(ExcelReader.class);
	private static int number=0;
	private static Window mockwin = mock(Window.class);
	
	@Before
	public void setupTests() {
		
		App.setTest(true);
		DatabaseAO.setdb(mockedDB);
		InputController.resetDB();
		InputController.setExcellReader(mockedEX);
		when(mockedDB.isHasid()).thenReturn(true);
		when(mockedDB.findById(1)).thenReturn(new StoredProperties(1,10));
		when(mockedDB.getAll()).thenReturn(Arrays.asList(new StoredProperties(1,10)));
		//when(Window.getWindow()).thenReturn(mockwin);
		//when(DatabaseAO.getdb()).thenReturn(mockedDB);
		
	}
	
	@Test
	public void handleFindTest() {
		DatabaseAO.setdb(mockedDB);
		InputController.handleinput("mode1");
		InputController.handleinput("find 1");
		assertThat(DatabaseAO.getdb().findById(1).getField1(), is(1));
		AppTest.test=App.getTestString();
		assertTrue(AppTest.test!=null);
	}
	
	@Test
	public void handleModeTest() {
		
		InputController.handleinput("mode1");
		assertTrue(App.getArg0().equals("1"));
		InputController.handleinput("mode2");
		assertTrue(App.getArg0().equals("2"));
		InputController.handleinput("mode3");
		assertTrue(App.getArg0().equals("3"));
		InputController.handleinput("mode4");
		InputController.handleinput("/help");
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
		DatabaseAO.setdb(mockedDB);
		InputController.handleinput("mode1");
		InputController.handleinput("read");
		number++;
		verify(mockedDB).getAll();
		InputController.handleinput("mode2");
		InputController.handleinput("read");
		number++;
		verify(mockedEX).read();
		InputController.handleinput("mode3");
		InputController.handleinput("read");
		number++;
		verify(mockedDB,times(2)).getAll();
		
	}
	
	@Test
	public void handleProcessTest() 
	{
		InputController.handleinput("mode1");
		InputController.handleinput("process");
		number++;
		verify(mockedDB).saveAll(mockedEX.read());
		InputController.handleinput("mode2");
		InputController.handleinput("process");
		number++;
		verify(mockedDB).saveAll(mockedEX.read());
		InputController.handleinput("mode3");
		InputController.handleinput("process");
		number++;
		verify(mockedDB).saveAll(mockedEX.read());
	}
	@After
	public void reset() {
		Mockito.reset(mockedDB);
		Mockito.reset(mockedEX);
		DatabaseAO.setdb(null);
		InputController.handleinput("exit");
	}
}
