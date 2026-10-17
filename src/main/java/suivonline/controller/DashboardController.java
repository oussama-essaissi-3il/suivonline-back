package suivonline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // GET /api/dashboard?debut=2026-10-01&fin=2026-10-31  (les dates sont facultatives)
    @GetMapping
    public DashboardDto dashboard(@RequestParam(required = false) LocalDate debut,
                                  @RequestParam(required = false) LocalDate fin) {
        return dashboardService.dashboard(debut, fin);
    }
}