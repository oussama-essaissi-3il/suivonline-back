package suivonline.exception;

// L'utilisateur est connecte mais n'a pas le droit -> HTTP 403
// ex : un developpeur declare du temps sur un projet auquel il n'est pas affecte
public class AccesRefuseException extends RuntimeException {
    public AccesRefuseException(String message) {
        super(message);
    }
}