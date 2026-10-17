package suivonline.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suivonline.dto.*;
import suivonline.model.*;
import suivonline.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DashboardService {

    private final ContributionRepository contributionRepository;
    private final ProjetRepository projetRepository;
    private final ProjetService projetService;

    public DashboardDto dashboard(LocalDate debut, LocalDate fin) {
        // Periode par defaut : du 1er du mois a aujourd'hui
        if (debut == null) debut = LocalDate.now().withDayOfMonth(1);
        if (fin == null) fin = LocalDate.now();

        // 1) Temps total par developpeur sur la periode
        List<TempsDeveloppeurDto> tempsParDev = contributionRepository.tempsParDeveloppeur(debut, fin);

        // 2) Projets en depassement
        List<ProjetDto> enDepassement = projetService.lister().stream()
                .filter(ProjetDto::enDepassement).toList();

        // 3) Projets clotures recemment (= dans les 30 derniers jours)
        List<ProjetDto> clotures = projetRepository
                .findByStatutAndDateClotureGreaterThanEqualOrderByDateClotureDesc(
                        StatutProjet.CLOTURE, LocalDate.now().minusDays(30))
                .stream().map(projetService::versDto).toList();

        return new DashboardDto(debut, fin, tempsParDev, enDepassement, clotures);
    }
}