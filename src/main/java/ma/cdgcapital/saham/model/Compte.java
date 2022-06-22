package ma.cdgcapital.saham.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Compte {

  private String idClientTitre;
  private String numeroCompteEspeceAttache;
  private String intitule;
  private String numeroCompte;
}
