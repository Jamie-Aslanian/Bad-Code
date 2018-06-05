package Excell_converter.todatabase;

import Excell_converter.todatabase.view.Converter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 

	
    extends TestCase
{
	public static String test;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    @org.junit.Test
    public void testApp()
    {
    	App.setTest(true);
    	assertTrue(App.isTest());
    	App.setArg0("1");
    	assertTrue(App.getArg0().equals("1"));
    	Converter test = new Converter();
    	App.setCon(test);
    	assertTrue(App.getCon().equals(test));
    	test.println("Welcome to the Game");
    	App.listener();
    	assertTrue(App.getArg0().equals("Welcome to the Game"));
    }
}
