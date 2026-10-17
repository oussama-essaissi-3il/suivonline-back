package suivonline.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "contribution")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contribution")
    private Integer id;

    @Column(name = "duree_minutes", nullable = false)
    private Integer dureeMinutes;

    @Column(name = "date_contrib", nullable = false)
    private LocalDate dateContrib;

    @ManyToOne
    @JoinColumn(name = "id_projet", nullable = false)
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;
}
