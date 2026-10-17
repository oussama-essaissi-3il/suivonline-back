package suivonline.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import suivonline.model.Role;
import suivonline.model.Utilisateur;
import suivonline.repository.UtilisateurRepository;

// CommandLineRunner : la methode run() s'execute une fois, au demarrage de l'application
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Seulement si aucun compte n'existe (sinon on ne touche a rien)
        if (utilisateurRepository.count() == 0) {
            utilisateurRepository.save(Utilisateur.builder()
                    .nom("Admin")
                    .prenom("Responsable")
                    .email("admin@suivonline.fr")
                    .motPasse(passwordEncoder.encode("admin123"))
                    .role(Role.RESPONSABLE)
                    .build());
        }
    }
}