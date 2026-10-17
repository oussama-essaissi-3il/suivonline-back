package suivonline.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suivonline.dto.*;
import suivonline.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
@RequiredArgsConstructor
public class ContributionController {

    private final ContributionService contributionService;

    @GetMapping
    public List<ContributionDto> toutes() {
        return contributionService.toutes();
    }
}