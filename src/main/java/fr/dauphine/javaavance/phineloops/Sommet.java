package fr.dauphine.javaavance.phineloops;

public class Sommet {

	private Coordonnees coordonnees;
	private Piece p;
	
	Sommet(Coordonnees coordonnees, Piece p) {
		this.coordonnees = coordonnees;
		this.setP(p);
	}
	/**
	 * @return p
	 */
	public Piece getP() {
		return p;
	}
	/**
	 * @param modifie p
	 */
	public void setP(Piece p) {
		this.p = p;
	}
	/**
	 * @return the coordonnees
	 */
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}
}
