package suivonline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public List<UtilisateurDto> lister() {
        return utilisateurService.lister();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UtilisateurDto creer(@Valid @RequestBody UtilisateurRequest req) {
        return utilisateurService.creer(req);
    }

    @PutMapping("/{id}")
    public UtilisateurDto modifier(@PathVariable Integer id, @Valid @RequestBody UtilisateurRequest req) {
        return utilisateurService.modifier(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Integer id) {
        utilisateurService.supprimer(id);
    }
}