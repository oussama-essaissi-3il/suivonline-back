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
public class ServeurService {

    private final ServeurRepository serveurRepository;
    private final ProjetRepository projetRepository;

    public List<ServeurDto> lister() {
        return serveurRepository.findAll().stream().map(ServeurDto::from).toList();
    }

    public ServeurDto creer(ServeurRequest req) {
        Serveur s = Serveur.builder().nom(req.nom()).description(req.description()).build();
        return ServeurDto.from(serveurRepository.save(s));
    }

    public ServeurDto modifier(Integer id, ServeurRequest req) {
        Serveur s = trouver(id);
        s.setNom(req.nom());
        s.setDescription(req.description());
        return ServeurDto.from(s);
    }

    public void supprimer(Integer id) {

        if (projetRepository.existsByServeurId(id)) {
            throw new RegleMetierException("Ce serveur heberge encore des projets");
        }
        serveurRepository.delete(trouver(id));
    }

    Serveur trouver(Integer id) {
        return serveurRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Serveur introuvable"));
    }
}