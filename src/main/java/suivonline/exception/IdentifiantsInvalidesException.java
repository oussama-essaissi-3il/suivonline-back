package suivonline.exception;

// Email ou mot de passe incorrect a la connexion -> HTTP 401
public class IdentifiantsInvalidesException extends RuntimeException {
    public IdentifiantsInvalidesException() {
        // Message volontairement vague : on ne dit pas si c'est l'email
        // ou le mot de passe qui est faux (sinon un pirate saurait quels emails existent)
        super("Email ou mot de passe incorrect");
    }
}