package objet;

public class FournisseurDeBecrouw
{
	private double nombresDeFournisseur;
	private double multiplicateurDuFournisseur;
	
	public FournisseurDeBecrouw(double nombresDeFournisseur, double multiplicateurDuFournisseur) {
		super();
		this.nombresDeFournisseur = nombresDeFournisseur;
		this.multiplicateurDuFournisseur = multiplicateurDuFournisseur;
	}

	public double getNombresDeFournisseur() {
		return nombresDeFournisseur;
	}

	public void augmenteNombresDeFournisseur() {
		this.nombresDeFournisseur +=1;
	}

	public double getMultiplicateurDuFournisseur() {
		return multiplicateurDuFournisseur;
	}

	public void setMultiplicateurDuFournisseur(double multiplicateurDuFournisseur) {
		this.multiplicateurDuFournisseur = multiplicateurDuFournisseur;
	}

	@Override
	public String toString() {
		return "FournisseurDeBecrouw [nombresDeFournisseur=" + nombresDeFournisseur + ", multiplicateurDuFournisseur="
				+ multiplicateurDuFournisseur + "]";
	}
	
	
	
}
