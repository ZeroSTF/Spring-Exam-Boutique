package tn.esprit.examnomprenom.services;

import tn.esprit.examnomprenom.entities.Boutique;
import tn.esprit.examnomprenom.entities.CentreCommercial;
import tn.esprit.examnomprenom.entities.Client;
import tn.esprit.examnomprenom.entities.enums.Categorie;

import java.util.List;

public interface IExamService {
    void ajoutCentre(CentreCommercial centre);
    void ajouterEtAffecterlisteBoutiques(List<Boutique>lb, Long idCentre);
    void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques);
    List<Client> listeClients(Long idBoutique);
    List<Boutique> listeBoutiques(Long idCentre);
    List<Client> listeDeClientsParCategorie(Categorie categorie);
    void nbreClientParGenre();
}
