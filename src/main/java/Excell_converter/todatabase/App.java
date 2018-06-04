package Excell_converter.todatabase;

import java.util.logging.Logger;

import Excell_converter.todatabase.view.Converter;
import Excell_converter.todatabase.view.Window;

public class App {
	private static final Logger LOGGER = Logger.getLogger( App.class.getName() );
	private static Window win;
	private static String arg0;
	private static Converter con = new Converter();private static Thread thread;

	public static void closeApplication() {
		App.win.stopwindow();
	}

	public static String getArg0() {
		return arg0;
	}

	public static Converter getCon() {
		return con;
	}

	public static void listener() {
		String arg = con.getline();
		arg0 = arg;
	}

	public static void main(String[] args){
		con.writer().println("WELCOME TO THE GREATEST EXCEL TO DB CONVERTER IN THE WORLD!");
		win = Window.getWindow();
		thread = new Thread(new Runnable() {
			public void run() {
				LOGGER.info("Loading");
				win.run(args);
			}
		});

		thread.start();
		win = Window.getWindow();
		LOGGER.info("Got the window");
	}

	public static void print(String m) {

		win = Window.getWindow();
		LOGGER.info("Got the window");
		win.printToView(m);

	}

	public static void setArg0(String arg0) {
		App.arg0 = arg0;
	}
	
	public static void setCon(Converter con) {
		App.con = con;
	}

}
