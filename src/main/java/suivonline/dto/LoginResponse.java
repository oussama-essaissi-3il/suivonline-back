package suivonline.dto;

// JSON renvoye apres connexion : le token JWT + qui est connecte
public record LoginResponse(String token, UtilisateurDto utilisateur) {}