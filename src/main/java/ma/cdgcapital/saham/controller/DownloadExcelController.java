package ma.cdgcapital.saham.controller;

import java.io.IOException;
import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Operation;
import ma.cdgcapital.saham.model.Position;
import ma.cdgcapital.saham.repository.CompteTitreRepository;
import ma.cdgcapital.saham.service.ExcelFileExporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadExcelController {

  private final CompteTitreRepository compteTitreRepository;

  public DownloadExcelController(CompteTitreRepository compteTitreRepository) {
    this.compteTitreRepository = compteTitreRepository;
  }

  @GetMapping("/api/v1/comptes/titres/{numeroCompte}/portefeuille")
  public List<Position> getPosition(@PathVariable String numeroCompte) {
    return compteTitreRepository.getPositions(numeroCompte);
  }

  @GetMapping("/api/v1/comptes/titres/{numeroCompte}")
  public Compte getCompteTitre(@PathVariable String numeroCompte) {
    return compteTitreRepository.getCompteTitre(numeroCompte);
  }

  @GetMapping(value = "api/v1/comptes/titres/{numeroCompte}/operations")
  public Operation getReleveChronologiques(@PathVariable String numeroCompte) {
    return compteTitreRepository.getReleveChronologiques(numeroCompte);
  }

  @GetMapping("/excel")
  public void test() throws IOException {
    ExcelFileExporter.generatePositionQuotidienneComptesTitres();
  }

  @GetMapping("/SMC")
  public void SMC() throws IOException {
    ExcelFileExporter.generateReleveChronoOperationsTitres();
  }
}
