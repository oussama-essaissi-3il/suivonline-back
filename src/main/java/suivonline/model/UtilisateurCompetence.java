package suivonline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateur_competence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisateurCompetence {

    @EmbeddedId
    private UtilisateurCompetenceId id;

    @ManyToOne
    @MapsId("idUtilisateur")
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @MapsId("idCompetence")
    @JoinColumn(name = "id_competence")
    private Competence competence;

    private String niveau;
}
