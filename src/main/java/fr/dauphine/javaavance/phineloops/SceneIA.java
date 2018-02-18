package fr.dauphine.javaavance.phineloops;
import java.net.URL;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneIA extends Application{
	private static int translation =55;
	private static int originX;
	private static int originY;

	public SceneIA(Stage primaryStage, Group root, Scene scene, int width,int height)
	{
		originX=300;
		originY=70;
		root.getChildren().clear();
		primaryStage.setTitle("Grille");
		Grille g=new Grille(width,height);
		g.genererGrilleResolue();
		g.genererGrilleAleatoire();
		Grille grilleInter=g.clonerGrille();
		g.resolution();
		int k=0;
		for(int i=0;i<g.getHauteur();i++)
		{
			for(int j=0;j<g.getLargeur();j++)
			{
				final URL imageURL = getClass().getClassLoader().getResource(grilleInter.getGrille()[i][j].getIdPiece()+".png"); 
		        final Image image = new Image(imageURL.toExternalForm()); 
		        final ImageView imageView = new ImageView(image); 
		      
		        final URL imageURL1 = getClass().getClassLoader().getResource(g.getGrille()[i][j].getIdPiece()+".png"); 
			    final Image image1 = new Image(imageURL1.toExternalForm());         
			    imageView.setPreserveRatio(true);
		        imageView.setSmooth(true);
			    Timeline tml = new Timeline();
		        KeyFrame movePlane = new KeyFrame(Duration.millis(100*(k+1)),new KeyValue(imageView.imageProperty(), image1));
		        k++;
		        tml.getKeyFrames().add(movePlane);
		        tml.play();
		        
		        imageView.setFitWidth(50); 
		        imageView.setFitHeight(50); 
		        imageView.setX(originX);
		        imageView.setY(originY);
		        originX+=translation;
		        root.getChildren().addAll(imageView);
			}
	        originY+=translation;
	        originX=300;
	       
	         
		} 	
		
		Button b1=new Button();
        b1.setText("Menu");  
        b1.setTranslateX(350);
        b1.setTranslateY(530);
        b1.setVisible(true);
        root.getChildren().add(b1);
        
        b1.setOnAction((ActionEvent event) -> { 
        	Menu ia=new Menu();
        	ia.start(primaryStage);
        });
        
		 primaryStage.setScene(scene);
		 primaryStage.show();
		
		 primaryStage.setScene(scene);
		 primaryStage.show();
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}