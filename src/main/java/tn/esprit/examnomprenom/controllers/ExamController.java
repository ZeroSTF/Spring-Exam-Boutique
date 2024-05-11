package tn.esprit.examnomprenom.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examnomprenom.entities.Boutique;
import tn.esprit.examnomprenom.entities.CentreCommercial;
import tn.esprit.examnomprenom.entities.Client;
import tn.esprit.examnomprenom.entities.enums.Categorie;
import tn.esprit.examnomprenom.services.IExamService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {
    private final IExamService examService;

    @PostMapping("/addCentre")
    public CentreCommercial addCentre(@RequestBody CentreCommercial centre){
        examService.ajoutCentre(centre);
        return centre;
    }

    @PostMapping("/addBoutiques/{idCentre}")
    public List<Boutique> addBoutiques(@RequestBody List<Boutique> boutiques, @PathVariable Long idCentre){
        examService.ajouterEtAffecterlisteBoutiques(boutiques, idCentre);
        return boutiques;
    }

    @PostMapping("/addClient/{idBoutiques}")
    public Client addClient(@RequestBody Client client, @PathVariable List<Long> idBoutiques){
        examService.ajouterEtAffecterClientBoutiques(client, idBoutiques);
        return client;
    }

    @GetMapping("/listeClients/{idBoutique}")
    public List<Client> listeClients(@PathVariable Long idBoutique){
        return examService.listeClients(idBoutique);
    }

    @GetMapping("/listeBoutiques/{idCentre}")
    public List<Boutique> listeBoutiques(@PathVariable Long idCentre){
        return examService.listeBoutiques(idCentre);
    }

    @GetMapping("/listeClientsByCategorie/{categorie}")
    public List<Client> listeClientsByCategorie(@PathVariable Categorie categorie){
        return examService.listeDeClientsParCategorie(categorie);
    }

}
