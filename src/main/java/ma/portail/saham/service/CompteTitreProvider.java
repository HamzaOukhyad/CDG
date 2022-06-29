package ma.portail.saham.service;

import java.util.List;
import ma.portail.saham.model.Compte;
import ma.portail.saham.model.Position;


public interface CompteTitreProvider {


    List<Position> getPositions(String numeroCompte);
    Compte getCompteTitre(String numeroCompte);

}
