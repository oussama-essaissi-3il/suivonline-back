package suivonline.dto;

// Temps total d'un developpeur (sur un projet ou sur une periode)
public record TempsDeveloppeurDto(Integer utilisateurId, String nom, String prenom, Long totalMinutes) {}
