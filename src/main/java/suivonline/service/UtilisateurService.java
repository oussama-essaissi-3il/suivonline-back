package suivonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ContributionRepository contributionRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UtilisateurDto> lister() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::from).toList();
    }

    public UtilisateurDto creer(UtilisateurRequest req) {
        // L'email est UNIQUE en base : on verifie avant pour renvoyer un message clair
        if (utilisateurRepository.existsByEmail(req.email())) {
            throw new RegleMetierException("Cet email est deja utilise");
        }
        Utilisateur u = Utilisateur.builder()
                .nom(req.nom())
                .prenom(req.prenom())
                .email(req.email())
                .motPasse(passwordEncoder.encode(req.motPasse()))   // on stocke le HASH
                .role(req.role())
                .build();
        return UtilisateurDto.from(utilisateurRepository.save(u));
    }

    public UtilisateurDto modifier(Integer id, UtilisateurRequest req) {
        Utilisateur u = trouver(id);
        u.setNom(req.nom());
        u.setPrenom(req.prenom());
        u.setEmail(req.email());
        u.setRole(req.role());
        // Mot de passe vide = on garde l'ancien
        if (req.motPasse() != null && !req.motPasse().isBlank()) {
            u.setMotPasse(passwordEncoder.encode(req.motPasse()));
        }
        return UtilisateurDto.from(u);
    }

    public void supprimer(Integer id) {
        // Sinon la base refuserait (cle etrangere contribution.id_utilisateur)
        if (contributionRepository.existsByUtilisateurId(id)) {
            throw new RegleMetierException("Cet utilisateur a des contributions, suppression impossible");
        }
        utilisateurRepository.delete(trouver(id));   // ses affectations partent avec lui
    }

    Utilisateur trouver(Integer id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Utilisateur introuvable"));
    }
}