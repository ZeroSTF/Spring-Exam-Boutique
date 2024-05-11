package tn.esprit.examnomprenom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examnomprenom.entities.Boutique;

public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
}
