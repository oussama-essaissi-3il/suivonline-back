package suivonline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import suivonline.model.Role;

// Creation / modification d'un compte par le responsable.
// motPasse : obligatoire a la creation, facultatif a la modification (vide = inchange).
// Cette regle depend du cas : elle est donc verifiee dans le SERVICE, pas ici.
public record UtilisateurRequest(
        @NotBlank @Size(max = 100) String nom,
        @NotBlank @Size(max = 100) String prenom,
        @NotBlank @Email @Size(max = 150) String email,
        String motPasse,
        @NotNull Role role
) {}