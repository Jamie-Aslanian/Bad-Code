package Excell_converter.todatabase.view;

public class Converter {

	String string;
	
	public Converter writer() 
	{
		return this;
	}
	public void println(String str) 
	{
		string=str;
	}
	public String getline() 
	{
		return string;
	}
}
