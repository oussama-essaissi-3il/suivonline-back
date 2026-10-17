package suivonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suivonline.dto.*;
import suivonline.exception.*;
import suivonline.model.*;
import suivonline.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final ContributionRepository contributionRepository;
    private final ServeurService serveurService;
    private final UtilisateurService utilisateurService;

    // ----- Lecture -----

    public List<ProjetDto> lister() {
        return projetRepository.findAll().stream().map(this::versDto).toList();
    }

    public ProjetDetailDto detail(Integer id) {
        Projet p = trouver(id);
        return new ProjetDetailDto(
                versDto(p),
                p.getUtilisateurs().stream().map(UtilisateurDto::from).toList(),
                contributionRepository.findByProjetIdOrderByDateContribDesc(id)
                        .stream().map(ContributionDto::from).toList(),
                contributionRepository.tempsParDeveloppeurSurProjet(id)
        );
    }

    public List<ProjetDto> projetsDuDeveloppeur(Integer utilisateurId) {
        return projetRepository.findByUtilisateursIdOrderByNomAsc(utilisateurId)
                .stream().map(this::versDto).toList();
    }

    // ----- Creation / modification -----

    public ProjetDto creer(ProjetRequest req) {
        Projet p = Projet.builder()
                .nom(req.nom())
                .description(req.description())
                .quotaHeures(req.quotaHeures())
                .statut(StatutProjet.EN_COURS)          // un nouveau projet est en cours
                .serveur(serveurService.trouver(req.serveurId()))
                .build();
        return versDto(projetRepository.save(p));
    }

    // Sert aussi a "modifier le quota d'heures" demande par le sujet
    public ProjetDto modifier(Integer id, ProjetRequest req) {
        Projet p = trouver(id);
        p.setNom(req.nom());
        p.setDescription(req.description());
        p.setQuotaHeures(req.quotaHeures());
        p.setServeur(serveurService.trouver(req.serveurId()));
        return versDto(p);
    }

    // REGLE : le responsable peut cloturer un projet
    public ProjetDto cloturer(Integer id) {
        Projet p = trouver(id);
        p.setStatut(StatutProjet.CLOTURE);
        p.setDateCloture(LocalDate.now());
        return versDto(p);
    }

    // ----- Affectations (relation n,n) -----

    public ProjetDetailDto affecter(Integer projetId, Integer utilisateurId) {
        Projet p = trouver(projetId);
        Utilisateur u = utilisateurService.trouver(utilisateurId);
        u.getProjets().add(p);         // cote PROPRIETAIRE -> ecrit dans la table affectation
        p.getUtilisateurs().add(u);    // cote inverse -> garde l'objet Java coherent
        return detail(projetId);
    }

    public ProjetDetailDto retirer(Integer projetId, Integer utilisateurId) {
        Projet p = trouver(projetId);
        Utilisateur u = utilisateurService.trouver(utilisateurId);
        u.getProjets().remove(p);
        p.getUtilisateurs().remove(u);
        return detail(projetId);
    }

    // ----- Utilitaires -----

    Projet trouver(Integer id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Projet introuvable"));
    }

    // REGLE : depassement = temps consomme > quota.
    // Quota en HEURES, contributions en MINUTES -> on multiplie le quota par 60
    ProjetDto versDto(Projet p) {
        long minutes = contributionRepository.totalMinutesParProjet(p.getId());
        boolean depassement = minutes > p.getQuotaHeures() * 60L;
        return new ProjetDto(p.getId(), p.getNom(), p.getDescription(), p.getQuotaHeures(),
                p.getStatut(), p.getDateCloture(), p.getServeur().getId(), p.getServeur().getNom(),
                minutes, depassement);
    }
}