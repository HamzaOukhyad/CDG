
package ma.cdgcapital.saham.repository;

import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Operation;
import ma.cdgcapital.saham.model.Position;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "${portail.services.titre_api.name}", url = "${portail.services.titre_api.url}")
public interface CompteTitreRepository {

  @GetMapping(value = "/api/v1/comptes/titres/{numeroCompte}/portefeuille")
  List<Position> getPositions(@PathVariable String numeroCompte);

  @GetMapping(value = "/api/v1/comptes/titres/{numeroCompte}")
  Compte getCompteTitre(@PathVariable String numeroCompte);
  
  @GetMapping(value = "api/v1/comptes/titres/{numeroCompte}/operations")
  Operation getReleveChronologiques(@PathVariable String numeroCompte);
}
