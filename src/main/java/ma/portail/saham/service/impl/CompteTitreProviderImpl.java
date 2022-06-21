package ma.portail.saham.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import ma.portail.saham.model.Compte;
import ma.portail.saham.model.Position;
import ma.portail.saham.repository.CompteTitreRepository;
import ma.portail.saham.service.CompteTitreProvider;

@Component
public class CompteTitreProviderImpl implements CompteTitreProvider {

	
	private final CompteTitreRepository comptetitrerepository;

	public CompteTitreProviderImpl(CompteTitreRepository comptetitrerepository) {
		super();
		this.comptetitrerepository = comptetitrerepository;
	}

	@Override
	public List<Position> getPositions(String numeroCompte) {
		
		return comptetitrerepository.getPositions(numeroCompte);
	}

	@Override
    public List<Compte> getCompteTitre(String numeroCompte){
		
		return comptetitrerepository.getCompteTitre(numeroCompte);
	}

}
