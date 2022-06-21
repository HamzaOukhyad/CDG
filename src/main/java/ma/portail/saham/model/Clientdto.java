package ma.portail.saham.model;

public class Clientdto {

	  private String idClientTitre;
	  private String numeroCompteEspeceAttache;
	  private String intitule;
	  
	public Clientdto(String idClientTitre, String numeroCompteEspeceAttache, String intitule) {
		super();
		this.idClientTitre = idClientTitre;
		this.numeroCompteEspeceAttache = numeroCompteEspeceAttache;
		this.intitule = intitule;
	}
	public Clientdto() {
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
