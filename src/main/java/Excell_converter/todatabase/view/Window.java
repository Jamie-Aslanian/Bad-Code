package Excell_converter.todatabase.view;

import java.awt.event.ActionListener;
import java.io.EOFException;

import Excell_converter.todatabase.App;
import Excell_converter.todatabase.controller.InputController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Window extends Application{
	 TextField notification;
	 Label lab1;
	 Label lab5;
	 Label lab4;
	 Label lab3;
	 Label lab2;
	 public boolean ready;
	 static Window win;
	 Stage stage;
	public void run(String[] arg) 
	  {
		  launch(arg);
	  }
	public Window() 
	  {
		  this.setwin();
	  }
	  
	private void setwin() {
		win=this;
		System.out.println(win);
	  }
	public static Window getWindow() 
	  {
		  if(win==null) 
		  {
			win = new Window();
			return  win;
		  }
		  System.out.println(win);
		  return win;
	  }
	  
	  @Override
	  public void start(Stage stage) {
	    Scene scene = new Scene(new Group(), 650, 250);
	    lab1= new Label(" ");
	    lab2= new Label(" ");
	    lab3= new Label(" ");
	    lab4= new Label(" ");
	    lab5= new Label(" ");
	    notification= new TextField();
	    
	    notification.setText("Label");
	    
	    notification.clear();
	    
	    notification.setOnAction(actionEvent -> {this.actionPerformed();});
	    
	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    grid.add(new Label("Type Command "), 0, 5);
	    grid.add(lab1, 1, 4);
	    grid.add(lab2, 1, 3);
	    grid.add(lab3, 1, 2);
	    grid.add(lab4, 1, 1);
	    grid.add(lab5, 1, 0);
	    grid.add(notification, 1, 5);
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);
	    stage.setScene(scene);
	    ready=true;
	    this.stage=stage;
	    stage.show();
	    this.PRINTTOVIEW(App.con.getline());
	    
	  }

	  // public void canYOUFEELTHECODEDEBTRACKINGUP?()
	public void actionPerformed() {
		System.out.println(notification.getText());
		this.PRINTTOVIEW(notification.getText());
		try {
		InputController.handleinput(notification.getText());
		}
		catch(Exception e) 
		{
			notification.setText(notification.getText().trim());
			//this.actionPerformed();
			this.PRINTTOVIEW("Get out of my Swamp.");
		}
		
	}
	
	public void PRINTTOVIEW(String Print) 
	{
		
		lab5.setText(lab4.getText());
		lab4.setText(lab3.getText());
		lab3.setText(lab2.getText());
		lab2.setText(lab1.getText());
		lab1.setText(Print);
		
	}

	public void stopwindow() 
	{
		stage.close();
		stage.hide();
		try {
		super.stop();}
		catch(Exception e){
			
		}
	}

	
}
