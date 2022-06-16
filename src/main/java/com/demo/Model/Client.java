package com.demo.Model;

import java.io.Serializable;

public class Client implements Serializable {

  private String id;
  private String idClientTitre;
  private String idClientEspece;
  private String devise;
  private String intitule;
  private String numeroCompte;
  private String codeGestionnaireFond;
  private String descriptionGestionnaireFond;
  private String[] numeroCompteEspeces;

  public Client() {}

  public Client(
      String id,
      String idClientTitre,
      String idClientEspece,
      String devise,
      String intitule,
      String numeroCompte,
      String codeGestionnaireFond,
      String descriptionGestionnaireFond,
      String[] numeroCompteEspeces) {
    this.id = id;
    this.idClientTitre = idClientTitre;
    this.idClientEspece = idClientEspece;
    this.devise = devise;
    this.intitule = intitule;
    this.numeroCompte = numeroCompte;
    this.codeGestionnaireFond = codeGestionnaireFond;
    this.descriptionGestionnaireFond = descriptionGestionnaireFond;
    this.numeroCompteEspeces = numeroCompteEspeces;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdClientTitre() {
    return idClientTitre;
  }

  public void setIdClientTitre(String idClientTitre) {
    this.idClientTitre = idClientTitre;
  }

  public String getIdClientEspece() {
    return idClientEspece;
  }

  public void setIdClientEspece(String idClientEspece) {
    this.idClientEspece = idClientEspece;
  }

  public String getDevise() {
    return devise;
  }

  public void setDevise(String devise) {
    this.devise = devise;
  }

  public String getIntitule() {
    return intitule;
  }

  public void setIntitule(String intitule) {
    this.intitule = intitule;
  }

  public String getNumeroCompte() {
    return numeroCompte;
  }

  public void setNumeroCompte(String numeroCompte) {
    this.numeroCompte = numeroCompte;
  }

  public String getCodeGestionnaireFond() {
    return codeGestionnaireFond;
  }

  public void setCodeGestionnaireFond(String codeGestionnaireFond) {
    this.codeGestionnaireFond = codeGestionnaireFond;
  }

  public String getDescriptionGestionnaireFond() {
    return descriptionGestionnaireFond;
  }

  public void setDescriptionGestionnaireFond(String descriptionGestionnaireFond) {
    this.descriptionGestionnaireFond = descriptionGestionnaireFond;
  }

  public String[] getNumeroCompteEspeces() {
    return numeroCompteEspeces;
  }

  public void setNumeroCompteEspeces(String[] numeroCompteEspeces) {
    this.numeroCompteEspeces = numeroCompteEspeces;
  }
}
