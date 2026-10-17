package suivonline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competence")
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nom;
}
