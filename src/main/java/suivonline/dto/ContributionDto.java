package suivonline.dto;

import suivonline.model.Contribution;

import java.time.LocalDate;

// On "aplatit" : au lieu d'envoyer tout l'objet Projet et tout l'objet Utilisateur,
// on envoie juste leur id et leur nom
public record ContributionDto(
        Integer id,
        Integer dureeMinutes,
        LocalDate dateContrib,
        Integer projetId,
        String projetNom,
        Integer utilisateurId,
        String utilisateurNomComplet
) {
    public static ContributionDto from(Contribution c) {
        return new ContributionDto(
                c.getId(),
                c.getDureeMinutes(),
                c.getDateContrib(),
                c.getProjet().getId(),
                c.getProjet().getNom(),
                c.getUtilisateur().getId(),
                c.getUtilisateur().getPrenom() + " " + c.getUtilisateur().getNom()
        );
    }
}