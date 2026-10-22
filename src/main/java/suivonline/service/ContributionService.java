package suivonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suivonline.dto.*;
import suivonline.exception.*;
import suivonline.model.*;
import suivonline.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final ProjetService projetService;
    private final AuthService authService;

    public List<ContributionDto> contributionsDe(Integer utilisateurId, String tri) {
        List<Contribution> liste = "projet".equals(tri)
                ? contributionRepository.findByUtilisateurIdOrderByProjetNomAscDateContribDesc(utilisateurId)
                : contributionRepository.findByUtilisateurIdOrderByDateContribDesc(utilisateurId);
        return liste.stream().map(ContributionDto::from).toList();
    }

    public List<ContributionDto> toutes() {
        return contributionRepository.findAllByOrderByDateContribDesc()
                .stream().map(ContributionDto::from).toList();
    }

    public ContributionDto declarer(String email, ContributionRequest req) {
        Utilisateur dev = authService.utilisateurConnecte(email);
        Projet projet = projetService.trouver(req.projetId());

        if (!dev.getProjets().contains(projet)) {
            throw new AccesRefuseException("Vous n'etes pas affecte a ce projet");
        }
        // Regle 2 : le projet ne doit pas etre cloture
        if (projet.getStatut() == StatutProjet.CLOTURE) {
            throw new RegleMetierException("Ce projet est cloture");
        }

        Contribution c = Contribution.builder()
                .utilisateur(dev)
                .projet(projet)
                .dureeMinutes(req.dureeMinutes())
                .dateContrib(req.dateContrib())
                .build();
        return ContributionDto.from(contributionRepository.save(c));
    }

    public RecapDto recap(Integer utilisateurId) {
        List<RecapProjetDto> parProjet = contributionRepository.recapParProjet(utilisateurId);
        long total = parProjet.stream().mapToLong(RecapProjetDto::totalMinutes).sum();
        return new RecapDto(parProjet, total);
    }
}