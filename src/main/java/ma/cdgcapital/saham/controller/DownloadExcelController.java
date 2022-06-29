package ma.cdgcapital.saham.controller;

import java.io.IOException;
import java.util.List;
package ma.cdgcapital.saham.controller;

import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Position;
import ma.cdgcapital.saham.repository.CompteTitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadExcelController {

  private final CompteTitreRepository compteTitreRepository;

  @Autowired
  public DownloadExcelController(CompteTitreRepository compteTitreRepository) {
    this.compteTitreRepository = compteTitreRepository;
  }

  @GetMapping("/position/{numeroCompte}")
  public List<Position> getPosition(@RequestParam("numeroCompte") String numeroCompte) {
    List<Position> position = compteTitreRepository.getPositions(numeroCompte);
    return position;
  }

  @GetMapping("/positionClient/{numeroCompte}")
  public Compte getCompteTitre(@RequestParam("numeroCompte") String numeroCompte) {
    Compte positionClientTitre = compteTitreRepository.getCompteTitre(numeroCompte);
    return positionClientTitre;
  }
}