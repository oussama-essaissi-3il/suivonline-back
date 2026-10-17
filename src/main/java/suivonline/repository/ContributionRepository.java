package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import suivonline.dto.RecapProjetDto;
import suivonline.dto.TempsDeveloppeurDto;
import suivonline.model.Contribution;

import java.time.LocalDate;
import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Integer> {

    // ---------- Requetes derivees (Spring ecrit le SQL a partir du nom) ----------

    // "Mes contributions" triees par date (la plus recente en premier)
    List<Contribution> findByUtilisateurIdOrderByDateContribDesc(Integer utilisateurId);

    // "Mes contributions" triees par nom de projet, puis par date
    List<Contribution> findByUtilisateurIdOrderByProjetNomAscDateContribDesc(Integer utilisateurId);

    // Detail d'un projet : ses contributions
    List<Contribution> findByProjetIdOrderByDateContribDesc(Integer projetId);

    // Responsable : toutes les contributions
    List<Contribution> findAllByOrderByDateContribDesc();

    // Avant de supprimer un utilisateur : a-t-il des contributions ?
    boolean existsByUtilisateurId(Integer utilisateurId);

    // ---------- Requetes JPQL (@Query) pour les calculs ----------

    // Temps consomme sur un projet, en minutes (0 si aucune contribution)
    @Query("SELECT COALESCE(SUM(c.dureeMinutes), 0L) FROM Contribution c WHERE c.projet.id = :projetId")
    Long totalMinutesParProjet(@Param("projetId") Integer projetId);

    // Recap d'un developpeur : son temps total PAR projet
    @Query("""
           SELECT new suivonline.dto.RecapProjetDto(p.id, p.nom, SUM(c.dureeMinutes))
           FROM Contribution c JOIN c.projet p
           WHERE c.utilisateur.id = :utilisateurId
           GROUP BY p.id, p.nom
           ORDER BY p.nom
           """)
    List<RecapProjetDto> recapParProjet(@Param("utilisateurId") Integer utilisateurId);

    // Detail d'un projet : qui a contribue et combien de temps
    @Query("""
           SELECT new suivonline.dto.TempsDeveloppeurDto(u.id, u.nom, u.prenom, SUM(c.dureeMinutes))
           FROM Contribution c JOIN c.utilisateur u
           WHERE c.projet.id = :projetId
           GROUP BY u.id, u.nom, u.prenom
           ORDER BY SUM(c.dureeMinutes) DESC
           """)
    List<TempsDeveloppeurDto> tempsParDeveloppeurSurProjet(@Param("projetId") Integer projetId);

    // Tableau de bord : temps total par developpeur sur une periode
    @Query("""
           SELECT new suivonline.dto.TempsDeveloppeurDto(u.id, u.nom, u.prenom, SUM(c.dureeMinutes))
           FROM Contribution c JOIN c.utilisateur u
           WHERE c.dateContrib BETWEEN :debut AND :fin
           GROUP BY u.id, u.nom, u.prenom
           ORDER BY SUM(c.dureeMinutes) DESC
           """)
    List<TempsDeveloppeurDto> tempsParDeveloppeur(@Param("debut") LocalDate debut,
                                                  @Param("fin") LocalDate fin);
}
