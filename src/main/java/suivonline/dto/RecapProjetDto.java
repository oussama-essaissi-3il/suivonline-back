package suivonline.dto;

// Temps total d'un developpeur sur un projet
public record RecapProjetDto(Integer projetId, String projetNom, Long totalMinutes) {}
