package ma.cdgcapital.saham.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position implements Serializable {

  private String numeroCompte;
  private String codeIsin;
  private String descriptionTitre;
  private int quantite;
}
