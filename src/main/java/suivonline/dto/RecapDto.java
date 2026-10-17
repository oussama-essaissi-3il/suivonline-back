package suivonline.dto;

import java.util.List;

// "Recapitulatif de mon temps" : detail par projet + total sur tous les projets
public record RecapDto(List<RecapProjetDto> parProjet, long totalMinutes) {}