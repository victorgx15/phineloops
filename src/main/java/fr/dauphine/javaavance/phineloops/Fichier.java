package fr.dauphine.javaavance.phineloops;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Fichier {

	public static void genererFichierSortie(Grille g,String nomFichier) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(nomFichier);
		writer.println(g.getHauteur());
		writer.println(g.getLargeur());
		
		for(int i=0;i<g.getHauteur();i++)
			for(int j=0;j<g.getLargeur();j++)
				writer.println(g.getGrille()[i][j].getNumPiece()+" "+g.getGrille()[i][j].getNumOrientation());

		writer.close();
	}
	
	public static Grille lireFichierEntree(String nomFichier) {
		Grille g = null;
		try{
			InputStream flux=new FileInputStream(nomFichier); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader in=new BufferedReader(lecture);
			int hauteur = Integer.parseInt(in.readLine());
			int largeur =Integer.parseInt(in.readLine());
			g=new Grille(hauteur, largeur);
			
			for(int i=0;i<hauteur;i++) {
				for(int j=0;j<largeur;j++) {
					String ligne=in.readLine();
					String [] tab=ligne.split(" ");
					int num=Integer.parseInt(tab[0]);
					int orientation=Integer.parseInt(tab[1]);
					g.getGrille()[i][j]= g.retournerPiece(num, orientation);
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return g;
	}

}
