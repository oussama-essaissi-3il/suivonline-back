package suivonline.dto;

import java.util.List;

public record ProjetDetailDto(
        ProjetDto projet,
        List<UtilisateurDto> developpeurs,
        List<ContributionDto> contributions,
        List<TempsDeveloppeurDto> tempsParDeveloppeur
) {}