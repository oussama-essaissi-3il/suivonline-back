package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Role;
import suivonline.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    // Connexion : retrouver un compte par son email
    // Optional = "peut-etre vide" (si aucun compte n'a cet email)
    Optional<Utilisateur> findByEmail(String email);

    // Creation d'un compte : l'email est-il deja pris ?
    boolean existsByEmail(String email);

    // Liste des developpeurs (pour le menu "affecter un developpeur")
    List<Utilisateur> findByRoleOrderByNomAsc(Role role);
}