package fr.dauphine.javaavance.phineloops;

public class Coordonnees {
	// coordonnees (en ligne puis colonnes) d'une piece
	private int ligne, colonne;
	
	public Coordonnees(int ligne,int colonne)
	{
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	/**
	 * @return colonne
	 */
	public int getColonne() {
		return colonne;
	}
	
	/**
	 * @return ligne
	 */
	public int getLigne() {
		return ligne;
	}
	
	// Retourne vrai si 2 Coordonnes ont la meme ligne et colonne, faux sinon
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Coordonnees)) return false;
		Coordonnees c = (Coordonnees) o;
		return this.ligne == c.getLigne() && this.colonne == c.getColonne();
	}
}
