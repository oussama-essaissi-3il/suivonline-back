package suivonline.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/serveurs")
@RequiredArgsConstructor
public class ServeurController {

    private final ServeurService serveurService;

    @GetMapping
    public List<ServeurDto> lister() {
        return serveurService.lister();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServeurDto creer(@Valid @RequestBody ServeurRequest req) {
        return serveurService.creer(req);
    }

    @PutMapping("/{id}")
    public ServeurDto modifier(@PathVariable Integer id, @Valid @RequestBody ServeurRequest req) {
        return serveurService.modifier(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Integer id) {
        serveurService.supprimer(id);
    }
}