package suivonline.exception;

// Une regle du sujet n'est pas respectee -> HTTP 400
// ex : contribution sur un projet cloture, email deja utilise
public class RegleMetierException extends RuntimeException {
    public RegleMetierException(String message) {
        super(message);
    }
}