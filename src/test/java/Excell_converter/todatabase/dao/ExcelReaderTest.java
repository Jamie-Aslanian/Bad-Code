package Excell_converter.todatabase.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExcelReaderTest {

	@Test
	public void getPathTest() {
		
		assertThat(ExcelReader.getpath(), is("C:/ide/Projects/Good Code/Bad-Code"));
		
	}
	
	@Test
	public void readTest() {
		ExcelReader ex = new ExcelReader();
		assertTrue(ex.read().get(0).getField1()==13);
		
	}
}
