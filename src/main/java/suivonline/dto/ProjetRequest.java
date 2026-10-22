package suivonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjetRequest(
        @NotBlank @Size(max = 150) String nom,
        String description,
        @NotNull @Min(0) Integer quotaHeures,
        @NotNull Integer serveurId
) {}