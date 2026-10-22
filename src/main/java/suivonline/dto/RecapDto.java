package suivonline.dto;

import java.util.List;

public record RecapDto(List<RecapProjetDto> parProjet, long totalMinutes) {}