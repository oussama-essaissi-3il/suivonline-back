package suivonline.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "serveur")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Serveur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_serveur")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Cote inverse de la relation 1,n (Projet.serveur est le cote proprietaire)
    @OneToMany(mappedBy = "serveur")
    private List<Projet> projets;
}