package suivonline.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ContributionRequest(
        @NotNull Integer projetId,
        @NotNull @Min(1) Integer dureeMinutes,
        @NotNull @PastOrPresent LocalDate dateContrib
) {}