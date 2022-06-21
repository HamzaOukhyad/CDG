package ma.portail.saham.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.portail.saham.model.Client;
import ma.portail.saham.repository.CompteTitreRepository;


@RestController
public class DownloadExcelController {

	@Autowired
	 private final CompteTitreRepository comptetitrerepository;
	 
	 public DownloadExcelController(CompteTitreRepository comptetitrerepository) {
	        this.comptetitrerepository = comptetitrerepository;
	    }
	 
	   @GetMapping("/position/{numeroCompte}")
	    public List<Client> getPosition(@RequestParam("numeroCompte") String numeroCompte) {
	        List<Client> position = comptetitrerepository.getPosition(numeroCompte);
	        return position;
	    }
	   @GetMapping("/positionClient/{numeroCompte}")
	    public List<Client> getPositionClient(@RequestParam("numeroCompte") String numeroCompte) {
	        List<Client> positionClient = comptetitrerepository.getPositionClient(numeroCompte);
	        return positionClient;
	    }
}
