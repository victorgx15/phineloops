package fr.dauphine.javaavance.phineloops;

import java.util.ArrayList;

public class Composante {
	private final int idComposante;
	
	//Liste des sommets et des voisins
	ArrayList<Sommet> sommets, voisins;
	
	Composante(int idComposante)
	{
		this.idComposante = idComposante;
		sommets=new ArrayList<Sommet>();
		voisins = new ArrayList<Sommet>();
	}
	public void differenceSommet(ArrayList<Composante> composantes ,Sommet sommet)
	{
		for(int i=0;i<composantes.size();i++)
		{
			if(composantes.get(i).getIdComposante()!=this.getIdComposante())
			{
				for(int j=0;j<composantes.get(i).getVoisins().size();j++)
				{
					if(composantes.get(i).getVoisins().get(j).getCoordonnees().equals(sommet.getCoordonnees()))
					{
						composantes.get(i).getVoisins().remove(composantes.get(i).getVoisins().get(j));
					}
				}
			}
		}
	}
	
	/**
	 * @return idComposante
	 */
	public int getIdComposante() {
		return idComposante;
	}
	
	public void ajouterVoisin(Sommet s) {
		getVoisins().add(s);
	}
	
	public void ajouterSommet(Sommet s) {
		sommets.add(s);
	}
	
	public Sommet getSommet(int i) {
		return sommets.get(i);
	}
	
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}
	/**
	 * @return the voisins
	 */
	public ArrayList<Sommet> getVoisins() {
		return voisins;
	}

}
