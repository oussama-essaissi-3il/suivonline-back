package suivonline.exception;

// On cherche un element qui n'existe pas -> HTTP 404
// ex : GET /api/projets/99 alors que le projet 99 n'existe pas
public class RessourceIntrouvableException extends RuntimeException {
    public RessourceIntrouvableException(String message) {
        super(message);
    }
}