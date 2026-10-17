package suivonline.dto;

import suivonline.model.Serveur;

// nbProjets : combien de projets heberge ce serveur (pratique pour l'affichage)
public record ServeurDto(Integer id, String nom, String description, int nbProjets) {

    public static ServeurDto from(Serveur s) {
        int nb = s.getProjets() == null ? 0 : s.getProjets().size();
        return new ServeurDto(s.getId(), s.getNom(), s.getDescription(), nb);
    }
}