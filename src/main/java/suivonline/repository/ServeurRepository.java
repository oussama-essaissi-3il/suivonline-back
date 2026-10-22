package suivonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivonline.model.Serveur;

public interface ServeurRepository extends JpaRepository<Serveur, Integer> {
}