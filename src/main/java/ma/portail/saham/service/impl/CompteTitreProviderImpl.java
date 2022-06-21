package ma.portail.saham.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ma.portail.saham.model.Client;
import ma.portail.saham.repository.CompteTitreRepository;
import ma.portail.saham.service.CompteTitreProvider;

public class CompteTitreProviderImpl implements CompteTitreProvider {

	@Autowired
	CompteTitreRepository comptetitrerepository;
	
	@Override
	public List<Client> getPosition(String numeroCompte) {
		
		return comptetitrerepository.getPosition(numeroCompte);
	}

	@Override
	public List<Client> getPositionClient(String numeroCompte) {
		
		return comptetitrerepository.getPositionClient(numeroCompte);
	}

}
