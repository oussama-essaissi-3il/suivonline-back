package suivonline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import suivonline.dto.ErreurDto;

// Transforme les exceptions de tous les controllers en JSON
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<ErreurDto> introuvable(RessourceIntrouvableException e) {
        return reponse(HttpStatus.NOT_FOUND, e.getMessage());        // 404
    }

    @ExceptionHandler(RegleMetierException.class)
    public ResponseEntity<ErreurDto> regleMetier(RegleMetierException e) {
        return reponse(HttpStatus.BAD_REQUEST, e.getMessage());      // 400
    }

    @ExceptionHandler(AccesRefuseException.class)
    public ResponseEntity<ErreurDto> accesRefuse(AccesRefuseException e) {
        return reponse(HttpStatus.FORBIDDEN, e.getMessage());        // 403
    }

    @ExceptionHandler(IdentifiantsInvalidesException.class)
    public ResponseEntity<ErreurDto> identifiants(IdentifiantsInvalidesException e) {
        return reponse(HttpStatus.UNAUTHORIZED, e.getMessage());     // 401
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErreurDto> validation(MethodArgumentNotValidException e) {
        return reponse(HttpStatus.BAD_REQUEST, "Donnees invalides : " + e.getBindingResult().getFieldError().getField());
    }

    private ResponseEntity<ErreurDto> reponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErreurDto(status.value(), message));
    }
}