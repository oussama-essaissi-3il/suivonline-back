package suivonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

// JSON recu : { "projetId": 1, "dureeMinutes": 90, "dateContrib": "2026-10-12" }
// Pas d'utilisateurId : on sait QUI declare grace au token (etape 6)
public record ContributionRequest(
        @NotNull Integer projetId,
        @NotNull @Min(1) Integer dureeMinutes,
        @NotNull @PastOrPresent LocalDate dateContrib
) {}