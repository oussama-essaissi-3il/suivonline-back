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

    @GetMapping
    public DashboardDto dashboard(@RequestParam(required = false) LocalDate debut,
                                  @RequestParam(required = false) LocalDate fin) {
        return dashboardService.dashboard(debut, fin);
    }
}