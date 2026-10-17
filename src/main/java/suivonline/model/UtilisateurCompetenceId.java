package suivonline.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UtilisateurCompetenceId implements Serializable {
    private Integer idUtilisateur;
    private Integer idCompetence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtilisateurCompetenceId that)) return false;
        return Objects.equals(idUtilisateur, that.idUtilisateur)
                && Objects.equals(idCompetence, that.idCompetence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idCompetence);
    }
}
