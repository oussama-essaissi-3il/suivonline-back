package suivonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import suivonline.dto.*;
import suivonline.exception.*;
import suivonline.model.*;
import suivonline.repository.*;
import suivonline.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest req) {
        Utilisateur u = utilisateurRepository.findByEmail(req.email())
                .orElseThrow(IdentifiantsInvalidesException::new);

        // Compare le mot de passe saisi avec le HASH stocke
        if (!passwordEncoder.matches(req.motPasse(), u.getMotPasse())) {
            throw new IdentifiantsInvalidesException();
        }
        return new LoginResponse(jwtService.genererToken(u), UtilisateurDto.from(u));
    }

    // Retrouve l'utilisateur connecte a partir de l'email lu dans son token
    public Utilisateur utilisateurConnecte(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RessourceIntrouvableException("Utilisateur introuvable"));
    }
}