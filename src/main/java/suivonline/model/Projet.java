package suivonline.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projet")
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "quota_heures", nullable = false)
    private Integer quotaHeures;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutProjet statut;

    // Remplie au moment de la cloture (pour "projets clotures recemment")
    @Column(name = "date_cloture")
    private LocalDate dateCloture;

    // Relation 1,n : plusieurs projets sur un serveur
    @ManyToOne
    @JoinColumn(name = "id_serveur", nullable = false)
    private Serveur serveur;

    // Cote "inverse" de la relation n,n : c'est Utilisateur.projets qui gere la table affectation
    @ManyToMany(mappedBy = "projets")
    @Builder.Default
    private Set<Utilisateur> utilisateurs = new HashSet<>();
}