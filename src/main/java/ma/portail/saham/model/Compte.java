package ma.portail.saham.model;

public class Compte {

	  private String idClientTitre;
	  private String numeroCompteEspeceAttache;
	  private String intitule;
	  private String numeroCompte;
	  
	public Compte(String idClientTitre, String numeroCompteEspeceAttache, String intitule,String numeroCompte) {
		super();
		this.idClientTitre = idClientTitre;
		this.numeroCompteEspeceAttache = numeroCompteEspeceAttache;
		this.intitule = intitule;
		this.numeroCompte=numeroCompte;
	}
	
	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public Compte() {
		super();
	}
	public String getIdClientTitre() {
		return idClientTitre;
	}
	public void setIdClientTitre(String idClientTitre) {
		this.idClientTitre = idClientTitre;
	}
	public String getNumeroCompteEspeceAttache() {
		return numeroCompteEspeceAttache;
	}
	public void setNumeroCompteEspeceAttache(String numeroCompteEspeceAttache) {
		this.numeroCompteEspeceAttache = numeroCompteEspeceAttache;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	 
	
}
