package fr.dauphine.javaavance.phineloops;

public class Piece {
	
	// Chaque piéce possède un id, un numéro, un numéro d'orientation,
	private final int idPiece, numPiece, numOrientation;
	
	//Liste des rotations possibles de la piece
	private final int [] rotationPossible;
	
	//Liste des connecteurs possibles (N nord, E est, O ouest, S sud)
	private String connecteur;
	
	//listeEst (resp. listeSud) est la liste des connecteurs possibles horizontalement (resp. vertivalement)
	private final int listeEst[], listeSud [];
	
	private int cc;
	
	Piece(int idPiece,int numPiece,int numOrientation, String connecteur,int[] listeEst, int[] listeSud, int[] rotationPossible)
	{
		this.idPiece = idPiece;
		this.numPiece = numPiece;
		this.numOrientation = numOrientation;
		this.connecteur = connecteur;
		this.listeEst = listeEst;
		this.listeSud = listeSud;
		this.rotationPossible = rotationPossible;
	}
	
	public static void RemplissagePieces()
	{
		int [] liste1= {1,2,3,4,6,9,13,14};
		int [] liste2= {5,7,8,10,11,12,15,16};
		int [] liste3= {1,3,4,5,7,10,14,15};
		int [] liste4= {2,6,8,9,11,12,13,16};
		
		Grille.ajouterPiece(new Piece(1,0,0,"",liste1,liste3, new int[] {}));
		Grille.ajouterPiece(new Piece(2,1,0,"N",liste1,liste3, new int[] {3,4,5}));
		Grille.ajouterPiece(new Piece(3,1,1,"E",liste2,liste3, new int[] {4,5,2}));
		Grille.ajouterPiece(new Piece(4,1,2,"S",liste1,liste4, new int[] {5,2,3}));
		Grille.ajouterPiece(new Piece(5,1,3,"O",liste1,liste3, new int[] {2,3,4}));
		Grille.ajouterPiece(new Piece(6,2,0,"NS",liste1,liste4, new int[] {7}));
		Grille.ajouterPiece(new Piece(7,2,1,"EO",liste2,liste3,new int[] {6}));
		Grille.ajouterPiece(new Piece(8,3,0,"NEO",liste2,liste3, new int[] {9,10,11}));
		Grille.ajouterPiece(new Piece(9,3,1,"NSE",liste2,liste4, new int[] {10,11,8}));
		Grille.ajouterPiece(new Piece(10,3,2,"ESO",liste2,liste4, new int[] {11,8,9}));
		Grille.ajouterPiece(new Piece(11,3,3,"NSO",liste1,liste4, new int[] {8,9,10}));
		Grille.ajouterPiece(new Piece(12,4,0,"NSEO",liste2,liste4, new int[] {}));
		Grille.ajouterPiece(new Piece(13,5,0,"NE",liste2,liste3, new int[] {14,15,16}));
		Grille.ajouterPiece(new Piece(14,5,1,"ES",liste2,liste4, new int[] {15,16,13}));
		Grille.ajouterPiece(new Piece(15,5,2,"SO",liste1,liste4, new int[] {16,13,14}));
		Grille.ajouterPiece(new Piece(16,5,3,"ON",liste1,liste3, new int[] {13,14,15}));
	}

	public int getIdPiece() {
		return idPiece;
	}

	/**
	 * @return numPiece
	 */
	public int getNumPiece() {
		return numPiece;
	}

	/**
	 * @return numOrientation
	 */
	public int getNumOrientation() {
		return numOrientation;
	}

	/**
	 * @return rotationPossible
	 */
	public int [] getRotationPossible() {
		return rotationPossible;
	}

	/**
	 * @return listeSud
	 */
	public int[] getListeSud() {
		return listeSud;
	}
	
	/**
	 * @return listeEst
	 */
	public int[] getListeEst() {
		return listeEst;
	}

	/**
	 * @return connecteur
	 */
	public String getConnecteur() {
		return connecteur;
	}

	/**
	 * @return the cc
	 */
	public int getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(int cc) {
		this.cc = cc;
	}

}
