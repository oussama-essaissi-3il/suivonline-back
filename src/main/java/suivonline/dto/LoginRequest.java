package suivonline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// JSON recu : { "email": "alice@suivonline.fr", "motPasse": "password" }
public record LoginRequest(
        @NotBlank @Email String email,
        @NotBlank String motPasse
) {}