package suivonline.dto;

import suivonline.model.StatutProjet;

import java.time.LocalDate;

/**
 * Un projet tel qu'affiche dans la liste du responsable.
 * minutesConsommees et enDepassement sont CALCULES par le service,
 * ils ne sont pas stockes en base.
 */
public record ProjetDto(
        Integer id,
        String nom,
        String description,
        Integer quotaHeures,
        StatutProjet statut,
        LocalDate dateCloture,
        Integer serveurId,
        String serveurNom,
        long minutesConsommees,
        boolean enDepassement
) {}