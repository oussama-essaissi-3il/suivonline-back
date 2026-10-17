package suivonline.dto;

import java.util.List;

// Page "detail d'un projet" : infos + devs affectes + contributions + temps par dev
public record ProjetDetailDto(
        ProjetDto projet,
        List<UtilisateurDto> developpeurs,
        List<ContributionDto> contributions,
        List<TempsDeveloppeurDto> tempsParDeveloppeur
) {}