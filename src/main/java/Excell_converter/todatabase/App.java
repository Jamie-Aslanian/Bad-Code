package Excell_converter.todatabase;

import java.io.IOException;


import Excell_converter.todatabase.view.Converter;
import Excell_converter.todatabase.view.Window;

/**
 * That's some very nice code I've got isn't it? Be a shame if Sonar was to tell me it's bad.
 *
 */



public class App 
{
	public static Window win;
	public static String arg0;
	public static Converter con = new Converter();
	public static Thread thread;
	
    public static void main( String[] args ) throws IOException
    {
        //System.out.println( "Hello World!" );
    	con.writer().println("WELCOME TO THE GREATEST EXCEL TO DB CONVERTER IN THE WORLD!");
    	win = Window.getWindow();
        thread = new Thread(new Runnable() {
            public void run()
            {	
            	System.out.println("Loading");
            	win.run(args);
            	
            }
            });
        //SpringApplication.run(App.class, args);
        //SpringApplication app = new SpringApplication(App.class);
        //app.setWebEnvironment(false); //<<<<<<<<<
        //ConfigurableApplicationContext ctx = app.run(args);

        thread.start();
        win = Window.getWindow();
        System.out.println("Got the Window: "+win);

        }
    
    
    
    public static void listener() 
    {
    	String arg = con.getline();
    	arg0=arg;
    }
    public static void print(String m) 
    {
        
        win = Window.getWindow();
        System.out.println("Got the Window: "+win);
    	win.PRINTTOVIEW(m);
    	
       
    }
    

   
}
