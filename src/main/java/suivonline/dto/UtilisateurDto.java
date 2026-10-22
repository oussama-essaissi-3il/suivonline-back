package suivonline.dto;

import suivonline.model.Role;
import suivonline.model.Utilisateur;

public record UtilisateurDto(Integer id, String nom, String prenom, String email, Role role) {

    public static UtilisateurDto from(Utilisateur u) {
        return new UtilisateurDto(u.getId(), u.getNom(), u.getPrenom(), u.getEmail(), u.getRole());
    }
}