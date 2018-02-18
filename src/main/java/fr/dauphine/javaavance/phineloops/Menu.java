package fr.dauphine.javaavance.phineloops;

import javafx.application.Application;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;



public class Menu extends Application {
  	private static int width;
  	private static int height;
  	public void setWidth(int width)
  	{
  		this.width=width;
  	}
  	
	public void setHeight(int height)
  	{
  		this.height=height;
  	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
  	@Override
	public void start(Stage primaryStage) {

		try {
			Group root = new Group();
	        Text txt1=new Text("Veuillez effectuer un choix ! ");
            txt1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            txt1.setTranslateX(190);
            txt1.setTranslateY(100);
            root.getChildren().add(txt1);

			//Ajout des boutons
            
            Button b1=new Button();
            b1.setText("Résolution automatique");  
            b1.setTranslateX(250);
            b1.setTranslateY(170);
            b1.setVisible(true);
            root.getChildren().add(b1);


            Button b2=new Button();
            b2.setText("Résolution manuelle");  
            b2.setTranslateX(265);
            b2.setTranslateY(240);
            b2.setVisible(true);
            root.getChildren().add(b2);
            
            
            Button b3=new Button();
            b3.setText("Quitter");  
            b3.setTranslateX(330);
            b3.setTranslateY(310);
            b3.setVisible(true);
            root.getChildren().add(b3);
            
            Scene scene= new Scene(root, 800, 600);
            
            b1.setOnAction((ActionEvent event) -> { 
            	SceneIA ia=new SceneIA(primaryStage,root,scene,width,height);
            });
            b2.setOnAction((ActionEvent event) -> {
            	SceneManuel manuel=new SceneManuel(primaryStage,root,scene,width,height);
            });
            b3.setOnAction((ActionEvent event) -> {System.exit(0);});
            
            
            
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
			scene.setFill(Color.DIMGRAY);
            primaryStage.setScene(scene);			
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        System.out.println("Vous avez introduit !!! "+args[0]+" et "+args[1]);
		launch(args);
	}
}