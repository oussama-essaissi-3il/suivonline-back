package suivonline.exception;

// Email ou mot de passe incorrect a la connexion -> HTTP 401
public class IdentifiantsInvalidesException extends RuntimeException {
    public IdentifiantsInvalidesException() {
        super("Email ou mot de passe incorrect");
    }
}