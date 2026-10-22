package suivonline.dto;

import java.time.LocalDate;
import java.util.List;

// Tableau de bord du responsable
public record DashboardDto(
        LocalDate debut,
        LocalDate fin,
        List<TempsDeveloppeurDto> tempsParDeveloppeur,
        List<ProjetDto> projetsEnDepassement,
        List<ProjetDto> projetsCloturesRecemment
) {}