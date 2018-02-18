package fr.dauphine.javaavance.phineloops;

import java.util.ArrayList;
import java.util.Random;

public class Grille {
	private static ArrayList <Piece> pieces = new ArrayList<Piece>();
	private final int hauteur, largeur;
	private Piece grille [][];
	private final int [] pieceOrientation = {1,4,2,4,1,4};
	
	public Grille(int hauteur,int largeur)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		grille = new Piece[hauteur][largeur];
		Piece.RemplissagePieces();
	}	
	
	public static void ajouterPiece(Piece p) {
		pieces.add(p);
	}
	
	public void genererGrilleResolue()
	{
		int piecesAutoriseesEst []= {1,2,4,5,6,11,15,16};
		int piecesAutoriseesSud []= {1,2,3,5,7,8,13,16};
		Piece pieceNord;
		Piece pieceOuest;
		for(int i=0;i<getHauteur();i++)
		{
			for(int j=0;j<getLargeur();j++)
			{
				// Après ces tests on a les pieces en haut et à gauche de la case courante
				if(i==0)
					pieceNord=pieces.get(0);
				else
					pieceNord=getGrille()[i-1][j];

				if(j==0)
					pieceOuest=pieces.get(0);
				else
					pieceOuest=getGrille()[i][j-1];

				// Recherche des pièces compatibles à la case
				ArrayList <Integer> liste=intersectionListes(pieceNord.getListeSud(),pieceOuest.getListeEst());
				if(j==getLargeur()-1)
				{
					ArrayList <Integer> liste1=intersectionListes(conversion(liste),piecesAutoriseesEst);
					liste=liste1;
				}
				if(i==getHauteur()-1)
				{
					ArrayList <Integer> liste2=intersectionListes(conversion(liste),piecesAutoriseesSud);
					liste=liste2;
				}
				int longueur=liste.size();
				int randomInt = (new Random()).nextInt(longueur);
				getGrille()[i][j]=retournerPiece((int)liste.get(randomInt));
			}
		}
	}
	
	public void genererGrilleResolueInverse()
	{
		int piecesAutoriseesEst []= {1,2,4,5,6,11,15,16};
		//int piecesAutoriseesEst []= {3,7,8,9,10,12,13,14};
		int piecesAutoriseesSud []= {1,2,3,5,7,8,13,16};
		//int piecesAutoriseesSud []= {4,6,9,10,11,12,14,15};
		Piece pieceNord;
		Piece pieceOuest;
		for(int i=0;i<getHauteur();i++)
		{
			for(int j=0;j<getLargeur();j++)
			{
				// Après ces tests on a les pieces en haut et à gauche de la case courante
				if(i==0)
				{
					pieceNord=pieces.get(0);
				}
				else
				{
					pieceNord=getGrille()[i-1][j];
				}
				if(j==0)
				{
					pieceOuest=pieces.get(0);
				}
				else
				{
					pieceOuest=getGrille()[i][j-1];
				}
				// Recherche des pièces compatibles à la case
				//System.out.println(complementaire(pieceNord.listeSud).length);
				//System.out.println(complementaire(pieceOuest.listeEst).length);

				ArrayList <Integer> liste=intersectionListes(complementaire(pieceNord.getListeSud()),complementaire(pieceOuest.getListeEst()));
				if(j==getLargeur()-1)
				{
					ArrayList <Integer> liste1=intersectionListes(conversion(liste),piecesAutoriseesEst);
					liste=liste1;
				}
				if(i==getHauteur()-1)
				{
					ArrayList <Integer> liste2=intersectionListes(conversion(liste),piecesAutoriseesSud);
					liste=liste2;
				}
				int longueur=liste.size();
				//System.out.println(longueur);
				Random r=new Random();
				int randomInt=r.nextInt(longueur);
				getGrille()[i][j]=retournerPiece((int)liste.get(randomInt));
			}
		}
	}
	
	public int [] complementaire(int [] liste) {
		ArrayList<Integer> elements=new ArrayList<Integer>();
		boolean test=false;
		for(int j=1;j<=16;j++)
		{
			test=false;
			for(int i=0;i<liste.length;i++)
			{
				{
					if(j==liste[i])
						test=true;
				}
				if(test==false)
					elements.add(j);
			}
		}
		return conversion(elements);
	}
	
	public void GrilleAvecComposantesConnexes(int nbcc)
	{
		ArrayList <Composante> composantes=new ArrayList<Composante>();
		// condition nécessaire 
		if(hauteur*largeur/2<nbcc)
		{
			System.out.println("Impossible de générer une telle grille  !");
		}
		else
		{
			//On commence par la création des composantes
			for(int i=0;i<nbcc;i++)
			{
				composantes.add(new Composante(i));
			}
			// Une composante possède au moins deux sommets adjacents et reliés
			for(int i=0;i<nbcc;i++)
			{
					Random r=new Random();
					int l=r.nextInt(hauteur);
					int c=r.nextInt(largeur);
					while(getGrille()[l][c]!=null)
					{
						l=r.nextInt(hauteur);
						c=r.nextInt(largeur);
					}
					int idpiece=r.nextInt(pieces.size())+1;
					Piece piece=retournerPiece(idpiece);
					while(!(testerExtremitees(piece,l,c)) && !(idpiece==1))
					{
						idpiece=r.nextInt(pieces.size())+1;
						piece=retournerPiece(idpiece);
					}
					// on est sûr que la pièce est bonne 
					piece.setCc(i);
					getGrille()[l][c]=piece;
					Sommet s=new Sommet(new Coordonnees(l,c),piece);
					System.out.println("Il s'agit de la pièce "+piece.getIdPiece()+" se trouvant à la position "+l+","+c);
					composantes.get(i).sommets.add(s);
					composantes.get(i).differenceSommet(composantes, s);
					ajouterVoisins(composantes.get(i),s);
					// génération d'un deuxième sommet
					if(composantes.get(i).voisins.size()!=0)
					{
						 idpiece=r.nextInt(pieces.size())+1;
						 piece=retournerPiece(idpiece);
						 l=composantes.get(i).voisins.get(0).getCoordonnees().getLigne();
						 c=composantes.get(i).voisins.get(0).getCoordonnees().getColonne();
						System.out.println("Il  de la voisine "+piece.getIdPiece()+" se trouvant à la position "+l+","+c);

						 
						while(!(testerPiece(piece,l,c)))
						{
							//System.out.println("Ceci est une bonne position");
							idpiece=r.nextInt(pieces.size())+1;
							piece=retournerPiece(idpiece);
						}
						System.out.println("Itération numéro "+i);
						piece.setCc(i);
						getGrille()[l][c]=piece;
						composantes.get(i).voisins.get(0).setP(piece);
						s=composantes.get(i).voisins.remove(0);
						ajouterVoisins(composantes.get(i),s);
						composantes.get(i).sommets.add(s);
						composantes.get(i).differenceSommet(composantes, s);					
					}
					
					
					
					
				System.out.println("La composante "+i+" possède les sommets :");
				for(Sommet so : composantes.get(i).sommets)
				{
					System.out.println("Sommet dont l'id est  "+so.getP().getIdPiece()+" dont les coordonnées sont ("+so.getCoordonnees().getLigne()+","+so.getCoordonnees().getColonne()+")");
				}
				for(Sommet so : composantes.get(i).voisins)
				{
					System.out.println("les coordonnées des voisins sont ("+so.getCoordonnees().getLigne()+","+so.getCoordonnees().getColonne()+")");
				}
				
			}
			
			boolean test=true;
			while(test==true)
			{
				for(int i=0;i<nbcc;i++)
				{
					if(composantes.get(i).voisins.size()!=0)
					{
						// création d'un nouveau voisin 
						Random r=new Random();
						int idpiece=r.nextInt(pieces.size())+1;
						Piece piece=retournerPiece(idpiece);
						int l=composantes.get(i).voisins.get(0).getCoordonnees().getLigne();
						int c=composantes.get(i).voisins.get(0).getCoordonnees().getColonne();
						while(!(testerPiece(piece,l,c)))
						{
							idpiece=r.nextInt(pieces.size())+1;
							piece=retournerPiece(idpiece);
						}
						piece.setCc(i);
						getGrille()[l][c]=piece;
						composantes.get(i).voisins.get(0).setP(piece);
						Sommet s=composantes.get(i).voisins.remove(0);
						ajouterVoisins(composantes.get(i),s);
						composantes.get(i).sommets.add(s);
						composantes.get(i).differenceSommet(composantes, s);						
					}
				}
				test=false;
				for(int i=0;i<nbcc;i++)
				{
					if(composantes.get(i).voisins.size()!=0)
					{
						test=true;
					}
				}
			}
		}
	}
	
	public boolean testerExtremitees(Piece piece , int l, int c)
	{
		if(l==0 && piece.getConnecteur().contains("N"))
				return false;
		
		if(l==hauteur-1 && piece.getConnecteur().contains("S"))
				return false;

		if(c==0 && piece.getConnecteur().contains("O"))
				return false;

		if(c==largeur-1 && piece.getConnecteur().contains("E"))
				return false;

		return true;
	}
	
	public boolean testerPiece(Piece piece, int l, int c)
	{
		Piece pieceNord;
		Piece pieceSud;
		Piece pieceEst;
		Piece pieceOuest;
		int cpt=0;
		if(l==0)
		{
			pieceNord=pieces.get(0);
		}
		else
		{
			pieceNord=getGrille()[l-1][c];
		}
		if(l==hauteur-1)
		{
			pieceSud=pieces.get(0);
		}
		else
		{
			pieceSud=getGrille()[l+1][c];
		}
		if(c==0)
		{
			pieceOuest=pieces.get(0);
		}
		else
		{
			pieceOuest=getGrille()[l][c-1];
		}
		if(c==largeur-1)
		{
			pieceEst=pieces.get(0);
		}
		else
		{
			pieceEst=getGrille()[l][c+1];
		}
		
		if(pieceNord!=null)
		{
			if(pieceNord.getCc()!=piece.getCc())
			{
				return false;
			}
			else
			{
				if(pieceNord.getConnecteur().contains("S") && !(piece.getConnecteur().contains("N")))
				{
					return false;
				}	
				if(pieceNord.getConnecteur().contains("S") && (piece.getConnecteur().contains("N")))
				{
					cpt++;
				}

			}
		}
		if(pieceSud!=null)
		{
			if(pieceSud.getCc()!=piece.getCc())
			{
				return false;
			}
			else
			{
				if(pieceSud.getConnecteur().contains("N") && !(piece.getConnecteur().contains("S")))
				{
					return false;
				}
				if(pieceSud.getConnecteur().contains("N") && (piece.getConnecteur().contains("S")))
				{
					cpt++;
				}		
			}
		}
		
		if(pieceOuest!=null)
		{
			if(pieceOuest.getCc()!=piece.getCc())
			{
				return false;
			}
			else
			{
				if(pieceOuest.getConnecteur().contains("E") && !(piece.getConnecteur().contains("O")))
				{
					return false;
				}
				if(pieceOuest.getConnecteur().contains("E") && (piece.getConnecteur().contains("O")))
				{
					cpt++;
				}
			}
		}
		
		if(pieceEst!=null)
		{
			if(pieceEst.getCc()!=piece.getCc())
			{
				return false;
			}
			else
			{
				if(pieceEst.getConnecteur().contains("O") && !(piece.getConnecteur().contains("E")))
				{
					return false;
				}
				if(pieceEst.getConnecteur().contains("O") && (piece.getConnecteur().contains("E")))
				{
					cpt++;
				}
			}
		}
		if(l==0)
		{
			if(piece.getConnecteur().contains("N"))
				return false;
		}
		if(l==hauteur-1)
		{
			if(piece.getConnecteur().contains("S"))
				return false;
		}
		if(c==0)
		{
			if(piece.getConnecteur().contains("O"))
				return false;
		}
		if(c==largeur-1)
		{
			if(piece.getConnecteur().contains("E"))
				return false;
		}
		
		if(cpt==0)
			return false;
		return true;
	}
	public void ajouterVoisins(Composante composante, Sommet sommet)
	{
		int i=sommet.getCoordonnees().getLigne();
		int j=sommet.getCoordonnees().getColonne();
		if(i+1!=hauteur && getGrille()[i+1][j]==null)
			composante.voisins.add(new Sommet(new Coordonnees(i+1,j),null));
		if(j+1!=largeur && getGrille()[i][j+1]==null)
			composante.voisins.add(new Sommet(new Coordonnees(i,j+1),null));
		if(i-1>=0 && getGrille()[i-1][j]==null)
			composante.voisins.add(new Sommet(new Coordonnees(i-1,j),null));
		if(j-1>=0 && getGrille()[i][j-1]==null)
			composante.voisins.add(new Sommet(new Coordonnees(i,j-1),null));
	}
	
	public void genererGrilleAleatoire()
	{
		Piece p = null;
		for(int i=0;i<getHauteur();i++)
		{
			for(int j=0;j<getLargeur();j++)
			{
				int num=getGrille()[i][j].getNumPiece();
				int nbOrientations=pieceOrientation[num];
				Random r=new Random();
				int orientation=r.nextInt(nbOrientations);
				for(Piece pie: pieces)
				{
					if((pie.getNumPiece()==num)&&(pie.getNumOrientation()==orientation))
					{
						 p=pie;
					}
				}
				getGrille()[i][j]=p;
			}
		}	
	}
	
	public ArrayList <Integer> intersectionListes(int []listeSud,int [] listeEst)
	{
		ArrayList<Integer> al=new ArrayList <Integer>();
		for(int i=0;i<listeSud.length;i++)
		{
			for(int j=0;j<listeEst.length;j++)
			{
				if(listeSud[i]==listeEst[j])
					al.add(listeSud[i]);
			}
		}
		return al;
	}
	public static Piece retournerPiece(int id)
	{
		for(Piece p:pieces)
		{
			if(p.getIdPiece()==id)
				return p;
		}
		return null;
	}
	
	public Piece retournerPiece(int num,int orientation)
	{
		for(Piece p:pieces)
		{
			if((p.getNumPiece()==num) && (p.getNumOrientation()==orientation))
				return p;
		}
		return null;
	}
	
	public int [] conversion(ArrayList<Integer> al)
	{
		int [] liste=new int [al.size()];
		for(int i=0;i<al.size();i++)
			liste[i]=(int) al.get(i);
		return liste;
	} 
	
	public boolean estResolue()
	{
		for(int i=0;i<getHauteur();i++)
		{
			for(int j=0;j<getLargeur();j++)
			{
				if(!testerPieceAdmissible(getGrille()[i][j],i,j))
					return false;
			}
		}
		return true;
	}
	public boolean heuristiqueResolution()
	{
		// on teste d'abord si la grille admet des solutions sur des conditions nécessaires, mais pas suffisantes
		String connecteurs ="";
		boolean resolue=false;
		if((getGrille()[0][0].getIdPiece()>=6 && getGrille()[0][0].getIdPiece()<=12) || (getGrille()[getHauteur()-1][0].getIdPiece()>=6 && getGrille()[getHauteur()-1][0].getIdPiece()<=12)  || (getGrille()[0][getLargeur()-1].getIdPiece()>=6 && getGrille()[0][getLargeur()-1].getIdPiece()<=12) || (getGrille()[getHauteur()-1][getLargeur()-1].getIdPiece()>=6 && getGrille()[getHauteur()-1][getLargeur()-1].getIdPiece()<=12))
			return resolue;
		else
		{
			for(int i=1;i<getHauteur()-1;i++)
			{
				if((getGrille()[i][0].getIdPiece()==12) || (getGrille()[i][getLargeur()-1].getIdPiece()==12))
					return resolue;
			}
			for(int j=1;j<getLargeur()-1;j++)
			{
				if((getGrille()[0][j].getIdPiece()==12) || (getGrille()[getHauteur()-1][j].getIdPiece()==12))
					return resolue;
			}
			// le nombre de connecteurs doit être pair
			for(int i=0;i<getHauteur();i++)
			{
				for(int j=0;j<getLargeur();j++)
				{
					connecteurs=connecteurs+getGrille()[i][j].getConnecteur();
				}
			}
			if(connecteurs.length()%2 ==1)
				return resolue;
		}

		return true;
	}
	
	public boolean resolution()
	{
		if(heuristiqueResolution()==false)
		{
			System.out.println("false");
			return false;
		}
		else
		{
			ArrayList<Grille> pile=new ArrayList<Grille>();
			ArrayList<Coordonnees> coordonnees=new ArrayList<Coordonnees>();
			int i=0;
			int j=0;
			Grille g;
			pile.add(this);
			coordonnees.add(new Coordonnees(0,0));
			while(pile.size()!=0)
			{
				g=pile.remove(pile.size()-1);
				Coordonnees c=coordonnees.remove(coordonnees.size()-1);
				i=c.getLigne();
				j=c.getColonne();
				
				
			/*	for(int x=0;x<g.getN();x++)
				{
					for(int y=0;y<g.getM();y++)
					{
						System.out.print(g.grille[x][y].idPiece+"\t");
					}
					System.out.println("");
				}*/
				
				if(g.estResolue())
				{
					//sequence=g.pileSequence;
					System.out.println("***** La grille est résolue *****");
					this.grille = g.getGrille();
					pile.clear();
					return true;
					
				}
					
				else
				{
					
					int id=getGrille()[i][j].getIdPiece();
					Piece p=retournerPiece(id);
					if(g.testerRotationAdmissible(p,i,j))
					{
						//ici faire l'affichage de la pièce
						//génération d'un nouvel objet Grille
						Grille nouvelleGrille=g.clonerGrille(p, i, j);
						pile.add(nouvelleGrille);
						//System.out.println("La taille de la séquence est "+nouvelleGrille.pileSequence.size());
						if(j<getLargeur()-1)
							coordonnees.add(new Coordonnees(i,j+1));
						else
							coordonnees.add(new Coordonnees(i+1,0));
					}
					for(int k=0;k<getGrille()[i][j].getRotationPossible().length;k++)
					{
						//ici faire l'affichage de la pièce
						id=getGrille()[i][j].getRotationPossible()[k];
						p=retournerPiece(id);
						if(g.testerRotationAdmissible(p,i,j))
						{
							Grille nouvelleGrille=g.clonerGrille(p, i, j);
							pile.add(nouvelleGrille);
							//System.out.println("La taille de la séquence est "+nouvelleGrille.pileSequence.size());
							if(j<getLargeur()-1)
								coordonnees.add(new Coordonnees(i,j+1));
							else
								coordonnees.add(new Coordonnees(i+1,0));
						}						
					}
					j++;
					if(j==getLargeur())
					{
						i++;
						if(i==getHauteur())
						{
							//return true;
						}
						j=0;
					}
				}
			}
			return false;	
		}
	}
	
	public Grille clonerGrille()
	{
		Grille g=new Grille(getHauteur(),getLargeur());
		for(int l=0;l<getHauteur();l++)
		{
			for(int k=0;k<getLargeur();k++)
			{
				g.getGrille()[l][k]=getGrille()[l][k];
			}
		}
		return g;
	}
	
	public Grille clonerGrille(Piece p, int i, int j)
	{
		Grille g=new Grille(getHauteur(),getLargeur());
		for(int l=0;l<getHauteur();l++)
		{
			for(int k=0;k<getLargeur();k++)
			{
				g.getGrille()[l][k]=getGrille()[l][k];
			}
		}
		g.getGrille()[i][j]=p;
		return g;
	}
	
	public boolean testerRotationAdmissible(Piece p,int i,int j)
	{
		Piece pieceNord,pieceOuest;
		if(i==0)
		{
			pieceNord=pieces.get(0);
		}
		else
		{
			pieceNord=getGrille()[i-1][j];
		}
		if(j==0)
		{
			pieceOuest=pieces.get(0);
		}
		else
		{
			pieceOuest=getGrille()[i][j-1];
		}

		if(i==getHauteur()-1)
		{
			if(p.getConnecteur().contains("S"))
			{
				return false;
			}
		}
		if(j==getLargeur()-1)
		{
			if(p.getConnecteur().contains("E"))
			{
				return false;
			}
		}
		if(!(((pieceNord.getConnecteur().contains("S")) && (p.getConnecteur().contains("N"))) || (!(pieceNord.getConnecteur().contains("S")) && (!(p.getConnecteur().contains("N"))))))
		{
			return false;
		}
		
		if(!(((pieceOuest.getConnecteur().contains("E")) && (p.getConnecteur().contains("O"))) || (!(pieceOuest.getConnecteur().contains("E")) && (!(p.getConnecteur().contains("O"))))))
		{
			return false;
		}
		return true;
	}
	
	public boolean testerPieceAdmissible(Piece p,int i,int j)
	{
		Piece pieceNord,pieceSud,pieceEst,pieceOuest;
		
		if(i==0)
			pieceNord=pieces.get(0);
		else
			pieceNord=getGrille()[i-1][j];

		if(j==0)
			pieceOuest=pieces.get(0);
		else
			pieceOuest=getGrille()[i][j-1];

		if(i==getHauteur()-1)
			pieceSud=pieces.get(0);
		else
			pieceSud=getGrille()[i+1][j];

		if(j==getLargeur()-1)
			pieceEst=pieces.get(0);
		else
			pieceEst=getGrille()[i][j+1];

		if(p.getConnecteur().contains("N") && !pieceNord.getConnecteur().contains("S"))
				return false;
		if(p.getConnecteur().contains("S") && !pieceSud.getConnecteur().contains("N"))
				return false;
		if(p.getConnecteur().contains("E") && !pieceEst.getConnecteur().contains("O"))
				return false;
		if(p.getConnecteur().contains("O") && !pieceOuest.getConnecteur().contains("E"))
				return false;
		return true;
	}
	
	public static void main (String [] args)
	{
		Grille g=new Grille(4,4);
		//g.GrilleResolue();
		//System.out.println(g.estResolue());
		//g.GrilleAvecComposantesConnexes(2);
		g.genererGrilleResolueInverse();

		for(int i=0;i<g.hauteur;i++)
		{
			for(int j=0;j<g.largeur;j++)
			{
				System.out.print(g.getGrille()[i][j].getIdPiece()+"\t");
			}
			System.out.println("");
		}
		
		//System.out.println(g.estResolue());
		//System.out.println(g.resolution());
		
	/*	System.out.println("La taille de la séquence finale est : "+g.pileSequence.size());

		for(int i=0;i<sequence.size();i++)
		{
			System.out.println("Les coordonnées sont : "+sequence.get(i).coordonnees.ligne+","+sequence.get(i).coordonnees.colonne+" la piece est : "+sequence.get(i).idPiece);
		}	
		*/
	}
	
	/**
	 * @return hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * @return grille
	 */
	public Piece[][] getGrille() {
		return grille;
	}
	
}
