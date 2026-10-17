package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Projet;
import suivonline.model.StatutProjet;

import java.time.LocalDate;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    // Avant de supprimer un serveur : heberge-t-il encore un projet ?
    boolean existsByServeurId(Integer serveurId);

    // Tableau de bord : projets CLOTURE depuis une date, les plus recents d'abord
    List<Projet> findByStatutAndDateClotureGreaterThanEqualOrderByDateClotureDesc(
            StatutProjet statut, LocalDate depuis);

    // "Mes projets" : parcourt la relation n,n (Projet.utilisateurs -> id)
    List<Projet> findByUtilisateursIdOrderByNomAsc(Integer utilisateurId);
}

