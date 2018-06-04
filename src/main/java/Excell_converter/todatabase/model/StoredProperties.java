package Excell_converter.todatabase.model;

import java.io.Serializable;

public class StoredProperties implements Serializable {

	private static final long serialVersionUID = 2368354838529655030L;
	
	private int field1;
	private int field2;
	
	public StoredProperties(int f1,int f2) 
	{
		field1=f1;
		field2=f2;
	}
	
	
	public int getField1() {
		return field1;
	}
	public void setField1(int field1) {
		this.field1 = field1;
	}
	public int getField2() {
		return field2;
	}
	public void setField2(int field2) {
		this.field2 = field2;
	}
	
	@Override
	public String toString() 
	{
		
		return "Field 1: "+field1+" -- Field 2: "+field2;
	}
	
}
