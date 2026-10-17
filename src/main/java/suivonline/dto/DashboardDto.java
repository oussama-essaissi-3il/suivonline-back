package suivonline.dto;

import java.time.LocalDate;
import java.util.List;

// Tableau de bord du responsable : exactement les 3 blocs demandes par le sujet
public record DashboardDto(
        LocalDate debut,
        LocalDate fin,
        List<TempsDeveloppeurDto> tempsParDeveloppeur,   // temps par dev sur la periode
        List<ProjetDto> projetsEnDepassement,            // projets en depassement
        List<ProjetDto> projetsCloturesRecemment         // projets clotures recemment
) {}