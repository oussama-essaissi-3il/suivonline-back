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

    List<Contribution> findByUtilisateurIdOrderByDateContribDesc(Integer utilisateurId);

    List<Contribution> findByUtilisateurIdOrderByProjetNomAscDateContribDesc(Integer utilisateurId);

    List<Contribution> findByProjetIdOrderByDateContribDesc(Integer projetId);

    List<Contribution> findAllByOrderByDateContribDesc();

    boolean existsByUtilisateurId(Integer utilisateurId);

    // Temps consomme sur un projet, en minutes
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
