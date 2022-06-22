package ma.portail.saham.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.portail.saham.model.Compte;
import ma.portail.saham.model.Position;
import ma.portail.saham.repository.CompteTitreRepository;

@RestController
public class DownloadExcelController {


	private final CompteTitreRepository comptetitrerepository;

	public DownloadExcelController(CompteTitreRepository comptetitrerepository) {
		this.comptetitrerepository = comptetitrerepository;
	}

	@GetMapping("/position/{numeroCompte}")
	public List<Position> getPosition(@RequestParam("numeroCompte") String numeroCompte) {
		List<Position> position = comptetitrerepository.getPositions(numeroCompte);
		return position;
	}

	@GetMapping("/positionClient/{numeroCompte}")
	public Compte getCompteTitre(@RequestParam("numeroCompte") String numeroCompte) {
		Compte positionClientTitre = comptetitrerepository.getCompteTitre(numeroCompte);
		return positionClientTitre;
	}
}
