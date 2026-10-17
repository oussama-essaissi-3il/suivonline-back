package suivonline.dto;

import suivonline.model.Role;
import suivonline.model.Utilisateur;

// Ce qu'on renvoie pour un utilisateur : JAMAIS le mot de passe
public record UtilisateurDto(Integer id, String nom, String prenom, String email, Role role) {

    // Methode "usine" : transforme une entite en DTO
    public static UtilisateurDto from(Utilisateur u) {
        return new UtilisateurDto(u.getId(), u.getNom(), u.getPrenom(), u.getEmail(), u.getRole());
    }
}