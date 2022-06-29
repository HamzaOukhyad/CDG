package ma.cdgcapital.saham.model.provider;

import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Operation;
import ma.cdgcapital.saham.model.Position;

public interface CompteTitreProvider {

  List<Position> getPositions(String numeroCompte);

  Compte getCompteTitre(String numeroCompte);

  Operation getReleveChronologiques(String numeroCompte);
}
