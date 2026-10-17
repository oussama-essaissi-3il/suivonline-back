package suivonline.dto;

import java.time.LocalDateTime;

// Format UNIQUE de toutes les erreurs renvoyees par l'API
// ex : { "status": 404, "message": "Projet 9 introuvable", "date": "2026-10-12T15:40:00" }
public record ErreurDto(int status, String message, LocalDateTime date) {

    // Constructeur raccourci : la date est remplie automatiquement
    public ErreurDto(int status, String message) {
        this(status, message, LocalDateTime.now());
    }
}