package tn.esprit.examnomprenom.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examnomprenom.entities.Boutique;
import tn.esprit.examnomprenom.entities.CentreCommercial;
import tn.esprit.examnomprenom.entities.Client;
import tn.esprit.examnomprenom.entities.enums.Categorie;
import tn.esprit.examnomprenom.entities.enums.Genre;
import tn.esprit.examnomprenom.repositories.BoutiqueRepository;
import tn.esprit.examnomprenom.repositories.CentreCommercialRepository;
import tn.esprit.examnomprenom.repositories.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements IExamService {
    private final CentreCommercialRepository centreCommercialRepository;
    private final BoutiqueRepository boutiqueRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public void ajoutCentre(CentreCommercial centre) {
        for(Boutique boutique : centre.getBoutiques()){
            boutique.setCentreCommercial(centre);
        }
        centreCommercialRepository.save(centre);
    }

    @Override
    public void ajouterEtAffecterlisteBoutiques(List<Boutique> lb, Long idCentre) {
        CentreCommercial centreCommercial = centreCommercialRepository.findById(idCentre).get();
        for(Boutique boutique : lb){
            boutique.setCentreCommercial(centreCommercial);
        }
        boutiqueRepository.saveAll(lb);

    }

    @Override
    @Transactional
    public void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques) {
        List<Boutique> boutiques = boutiqueRepository.findAllById(idBoutiques);
        for(Boutique boutique : boutiques){
            boutique.getClients().add(client);
        }
        clientRepository.save(client);
    }

    @Override
    public List<Client> listeClients(Long idBoutique) {
        Boutique boutique = boutiqueRepository.findById(idBoutique).get();
        return boutique.getClients();
    }

    @Override
    public List<Boutique> listeBoutiques(Long idCentre) {
        CentreCommercial centreCommercial = centreCommercialRepository.findById(idCentre).get();
        return centreCommercial.getBoutiques();
    }

    @Override
    public List<Client> listeDeClientsParCategorie(Categorie categorie) {
        return clientRepository.findByBoutiques_Categorie(categorie);
    }

    @Override
    @Scheduled(fixedRate = 30000)
    public void nbreClientParGenre() {
        List<Client> clients = clientRepository.findAll();
        long nbreHomme = clients.stream().filter(client -> client.getGenre().equals(Genre.MASCULIN)).count();
        long nbreFemme = clients.stream().filter(client -> client.getGenre().equals(Genre.FEMININ)).count();
        log.info("Nombre de clients Homme : {}", nbreHomme);
        log.info("Nombre de clients Femme : {}", nbreFemme);
    }
}
