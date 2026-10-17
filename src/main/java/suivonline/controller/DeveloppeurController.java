package suivonline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.util.List;

// /api/me/... : "MES" donnees. L'utilisateur est deduit du TOKEN, jamais de l'URL
@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class DeveloppeurController {

    private final AuthService authService;
    private final ProjetService projetService;
    private final ContributionService contributionService;

    // Mes projets
    @GetMapping("/projets")
    public List<ProjetDto> mesProjets(Authentication auth) {
        return projetService.projetsDuDeveloppeur(monId(auth));
    }

    // Mes contributions : ?tri=date (par defaut) ou ?tri=projet
    @GetMapping("/contributions")
    public List<ContributionDto> mesContributions(Authentication auth,
                                                  @RequestParam(defaultValue = "date") String tri) {
        return contributionService.contributionsDe(monId(auth), tri);
    }

    // Declarer une contribution
    @PostMapping("/contributions")
    @ResponseStatus(HttpStatus.CREATED)
    public ContributionDto declarer(Authentication auth, @Valid @RequestBody ContributionRequest req) {
        return contributionService.declarer(auth.getName(), req);
    }

    // Recapitulatif de mon temps
    @GetMapping("/recap")
    public RecapDto recap(Authentication auth) {
        return contributionService.recap(monId(auth));
    }

    // auth.getName() = l'email mis dans le token -> on retrouve l'id
    private Integer monId(Authentication auth) {
        return authService.utilisateurConnecte(auth.getName()).getId();
    }
}