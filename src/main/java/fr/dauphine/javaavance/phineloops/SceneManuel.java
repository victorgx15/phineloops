package fr.dauphine.javaavance.phineloops;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SceneManuel extends Application{
	private static int translation =55;
	private static int originX=300;
	private static int originY=70;
	private static Grille grilleGlobale=null;



	public SceneManuel(Stage primaryStage, Group root, Scene scene,int width,int height)
	{
		originX=300;
		originY=70;
		ArrayList<ImageView> listeImageView=new ArrayList<ImageView>();
		ArrayList<Piece> listePieces=new ArrayList<Piece>();
		ArrayList<Coordonnees> listeCoordonnees=new ArrayList<Coordonnees>();
		root.getChildren().clear();
		primaryStage.setTitle("Grille");
		Grille g=new Grille(width,height);
		g.genererGrilleResolue();
		g.genererGrilleAleatoire();
		grilleGlobale=g;
		int k=0;
		for(int i=0;i<g.getHauteur();i++)
		{
			for(int j=0;j<g.getLargeur();j++)
			{
		        final URL imageURL = getClass().getClassLoader().getResource(g.getGrille()[i][j].getIdPiece()+".png"); 
			    final Image image = new Image(imageURL.toExternalForm());   
		        final ImageView imageView = new ImageView(image); 
			    imageView.setPreserveRatio(true);
		        imageView.setSmooth(true);
		        imageView.setFitWidth(50); 
		        imageView.setFitHeight(50); 
		        imageView.setX(originX);
		        imageView.setY(originY);
		        originX+=translation;
		        
		        root.getChildren().addAll(imageView);
		        listeImageView.add(imageView);
		        listePieces.add(g.getGrille()[i][j]);
		        listeCoordonnees.add(new Coordonnees(i,j));
		        
		        imageView.setOnMouseClicked(new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	for(int i=0;i<listeImageView.size();i++)
		            	{
		            		if(listeImageView.get(i)==imageView)
		            		{
		            			final URL imageURL = getClass().getClassLoader().getResource(listePieces.get(i).getRotationPossible()[0]+".png"); 
		            			Piece p=Grille.retournerPiece(listePieces.get(i).getRotationPossible()[0]);
		            			listePieces.set(i, p);
		            			Coordonnees c=listeCoordonnees.get(i);
		            			//g.grille[i][j]=p;
		            			grilleGlobale.getGrille()[c.getLigne()][c.getColonne()]=p;
		         			    final Image image = new Image(imageURL.toExternalForm());   
				                imageView.setImage(image);
				                if(grilleGlobale.estResolue())
				                {
				                	System.out.println("BRAAAAAVOOOOO");				                	
				                	 Text txt1=new Text("Bravo : Grille résolue");
				                     txt1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				                     txt1.setTranslateX(300);
				                     txt1.setTranslateY(originY+60);
				                     root.getChildren().add(txt1);
				                }
		            		}
		            	}
		            }
		        });
			}
	        originY+=translation;
	        originX=300;
		} 
		
		 Button b1=new Button();
         b1.setText("Menu");  
         b1.setTranslateX(350);
         b1.setTranslateY(originY);
         b1.setVisible(true);
         root.getChildren().add(b1);
         
         b1.setOnAction((ActionEvent event) -> { 
         	Menu ia=new Menu();
         	ia.start(primaryStage);
         });
         
		 primaryStage.setScene(scene);
		 primaryStage.show();
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
