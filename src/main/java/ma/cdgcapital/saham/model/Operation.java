package ma.cdgcapital.saham.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {

  private List<Operation> content;
  private String dateValeur;
  private String dateOperation;
  private String referenceCro;
  private String libelle;
  private String codeIsin;
  private String descriptionTitre;
  private String quantite;
  private String prix;
  private String montantBrut;
  private String montantNet;
}
