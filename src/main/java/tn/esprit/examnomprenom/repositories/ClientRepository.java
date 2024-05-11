package tn.esprit.examnomprenom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examnomprenom.entities.Client;
import tn.esprit.examnomprenom.entities.enums.Categorie;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByBoutiques_Categorie(Categorie categorie);
}
