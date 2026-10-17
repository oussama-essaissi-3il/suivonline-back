package suivonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Creation / modification d'un projet (le statut n'est pas ici :
// un projet nait EN_COURS et on le cloture par une action dediee)
public record ProjetRequest(
        @NotBlank @Size(max = 150) String nom,
        String description,
        @NotNull @Min(0) Integer quotaHeures,
        @NotNull Integer serveurId
) {}