package suivonline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
public class ProjetController {

    private final ProjetService projetService;

    @GetMapping
    public List<ProjetDto> lister() {
        return projetService.lister();
    }

    @GetMapping("/{id}")
    public ProjetDetailDto detail(@PathVariable Integer id) {
        return projetService.detail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetDto creer(@Valid @RequestBody ProjetRequest req) {
        return projetService.creer(req);
    }

    // Modifier
    @PutMapping("/{id}")
    public ProjetDto modifier(@PathVariable Integer id, @Valid @RequestBody ProjetRequest req) {
        return projetService.modifier(id, req);
    }

    // Cloturer
    @PatchMapping("/{id}/cloture")
    public ProjetDto cloturer(@PathVariable Integer id) {
        return projetService.cloturer(id);
    }

    // Affecter
    @PostMapping("/{id}/developpeurs/{utilisateurId}")
    public ProjetDetailDto affecter(@PathVariable Integer id, @PathVariable Integer utilisateurId) {
        return projetService.affecter(id, utilisateurId);
    }

    // Retirer
    @DeleteMapping("/{id}/developpeurs/{utilisateurId}")
    public ProjetDetailDto retirer(@PathVariable Integer id, @PathVariable Integer utilisateurId) {
        return projetService.retirer(id, utilisateurId);
    }
}