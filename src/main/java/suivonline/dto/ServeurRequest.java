package suivonline.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ServeurRequest(
        @NotBlank @Size(max = 100) String nom,
        String description
) {}