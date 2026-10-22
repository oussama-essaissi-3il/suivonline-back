package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Projet;
import suivonline.model.StatutProjet;

import java.time.LocalDate;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    boolean existsByServeurId(Integer serveurId);

    List<Projet> findByStatutAndDateClotureGreaterThanEqualOrderByDateClotureDesc(
            StatutProjet statut, LocalDate depuis);

    List<Projet> findByUtilisateursIdOrderByNomAsc(Integer utilisateurId);
}

