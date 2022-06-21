package ma.portail.saham.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.portail.saham.model.Compte;
import ma.portail.saham.model.Position;


@Repository
@FeignClient(name = "${portail.services.titre_api.name}", url = "${portail.services.titre_api.url}")
public interface CompteTitreRepository {
	
	   @RequestMapping(method = RequestMethod.GET, value = "/api/v1/comptes/titres/{numeroCompte}/portefeuille")
	    List<Position> getPositions(@RequestParam("numeroCompte") String numeroCompte);

	    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/comptes/titres/{numeroCompte}", produces = "application/json")

	    List<Compte> getCompteTitre(@RequestParam("numeroCompte") String numeroCompte);
	    
	  

}

