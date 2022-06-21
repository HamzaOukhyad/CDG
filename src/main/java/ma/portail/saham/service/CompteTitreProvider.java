package ma.portail.saham.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ma.portail.saham.model.Client;

public interface CompteTitreProvider {


    List<Client> getPosition(String numeroCompte);
    List<Client> getPositionClient(String numeroCompte);

}
