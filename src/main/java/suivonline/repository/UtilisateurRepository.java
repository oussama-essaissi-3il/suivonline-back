package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Role;
import suivonline.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    // Liste des developpeurs
    List<Utilisateur> findByRoleOrderByNomAsc(Role role);
}