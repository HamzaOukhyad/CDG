package ma.portail.saham.model;

import java.io.Serializable;

public class Client implements Serializable {

  private String numeroCompte;
  private String codeIsin;
  private String descriptionTitre;
  private int quantite;
  
  
public String getNumeroCompte() {
	return numeroCompte;
}
public void setNumeroCompte(String numeroCompte) {
	this.numeroCompte = numeroCompte;
}
public String getCodeIsin() {
	return codeIsin;
}
public void setCodeIsin(String codeIsin) {
	this.codeIsin = codeIsin;
}
public String getDescriptionTitre() {
	return descriptionTitre;
}
public void setDescriptionTitre(String descriptionTitre) {
	this.descriptionTitre = descriptionTitre;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}
public Client() {
	super();
}
public Client(String numeroCompte, String codeIsin, String descriptionTitre, int quantite) {
	super();
	this.numeroCompte = numeroCompte;
	this.codeIsin = codeIsin;
	this.descriptionTitre = descriptionTitre;
	this.quantite = quantite;
}
  
}
