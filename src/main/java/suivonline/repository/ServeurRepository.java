package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Serveur;

// Aucune methode a ajouter : findAll, findById, save, delete suffisent
public interface ServeurRepository extends JpaRepository<Serveur, Integer> {
}