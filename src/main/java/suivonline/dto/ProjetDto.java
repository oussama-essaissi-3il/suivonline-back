package suivonline.dto;

import suivonline.model.StatutProjet;

import java.time.LocalDate;

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