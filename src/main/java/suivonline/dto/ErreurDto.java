package suivonline.dto;

import java.time.LocalDateTime;

public record ErreurDto(int status, String message, LocalDateTime date) {

    public ErreurDto(int status, String message) {
        this(status, message, LocalDateTime.now());
    }
}