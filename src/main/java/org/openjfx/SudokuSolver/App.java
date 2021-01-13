package org.openjfx.SudokuSolver;

import java.awt.Color;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class App extends Application {
	TextField[][] textFields = new TextField[9][9];
	private int row;
	private int col;

    @Override
    public void start(Stage stage) {
    	GridPane grid = new GridPane();
    	grid.setStyle("-fx-background-color: rgb(250, 250, 250);\r\n"
    			+ "    -fx-vgap: 10;\r\n"
    			+ "    -fx-hgap: 10;\r\n"
    			+ "    -fx-padding: 9");
    	
        grid.setAlignment(Pos.CENTER);
        for (row=0; row<9; row++) {
        	if (row%5==0 && row!=0) {
        		TextField txt = new TextField();
        		txt.setAlignment(Pos.CENTER);
        		txt.setStyle("-fx-background-color: rgb(165, 67, 64)");
        		grid.add(txt, col, row);
        	}
        	else if (row%6==3 && row!=0){
        		TextField txt = new TextField();
        		txt.setAlignment(Pos.CENTER);
        		txt.setStyle("-fx-background-color: rgb(182, 217, 210)");
        		grid.add(txt, col, row);
        	}
     	   for (col=0; col<9; col++) {
     		   TextField txt = new TextField();
       		   txt.setAlignment(Pos.CENTER);     		 
       		   textFields[col][row] = txt;
     		   grid.add(txt,col,row);
     		  
     	   }
        }
        
        
        Button btn = new Button("Reset");
        HBox box = new HBox(10);
        box.getChildren().add(btn);
        box.setAlignment(Pos.BOTTOM_LEFT);
        grid.add(box,3,9,3,1);
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		for (row=0; row<9; row++) {
        	     	   for (col=0; col<9; col++) {        	     
        	     		   textFields[col][row].setText("");
        	     		   textFields[col][row].setStyle("-fx-background-color:rgb(255,255,255)");
        	     		   
        	     	   }
        	        }
        		
        		}
        	
        	});
        
        
        btn = new Button("Solve");
        box = new HBox(10);
        box.getChildren().add(btn);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(box, 4,9,2,1);
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		Solver solve = new Solver();
        		Grid entity = new Grid();
        		for (row=0; row<9; row++) {
     	     	   for (col=0; col<9; col++) {
     	     		  
     	     		   try {
     	     			 Integer ir = Integer.valueOf(textFields[col][row].getText().trim());
     	     			 entity.setNum(col, row, ir);
     	     		   }
     	     		   catch(NumberFormatException e) {
	     			   
     	     		   }
     	     	   	}
     	     	}
        		
        		Grid ans = solve.solution(entity);
        		    for (row=0; row<9; row++) {
        	     	   for (col=0; col<9; col++) {
        	     		   if (textFields[col][row].getText().trim().equals("")) {
        	     			   if (ans==null) {
        	     				  textFields[col][row].setStyle("-fx-background-color:rgb(165, 67, 64)");
        	     			   		System.out.print("Try Again ");
        	     			   }
        	     			   else {
        	     				  textFields[col][row].setStyle("-fx-background-color:rgb(182, 217, 210)");
        	     				  textFields[col][row].setText(""+ans.getNum(col, row));
        	     			   }
        	     		   }
        	     		   
        	     		   else {
        	     			  textFields[col][row].setStyle("-fx-background-color:rgb(255,255,255)");
        	     		   }
        	     		   		  
        	     	   }
        	        }
        		
        		}
        	
        });
         
        stage.setTitle("Sudoku Solver");
        var scene = new Scene(grid, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}