package ma.cdgcapital.saham.repository;

import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Position;
import ma.cdgcapital.saham.model.provider.CompteTitreProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompteTitreProviderImpl implements CompteTitreProvider {

  private final CompteTitreRepository comptetitrerepository;

  @Autowired
  public CompteTitreProviderImpl(CompteTitreRepository comptetitrerepository) {
    this.comptetitrerepository = comptetitrerepository;
  }

  @Override
  public List<Position> getPositions(String numeroCompte) {

    return comptetitrerepository.getPositions(numeroCompte);
  }

  @Override
  public Compte getCompteTitre(String numeroCompte) {

    return comptetitrerepository.getCompteTitre(numeroCompte);
  }
}
