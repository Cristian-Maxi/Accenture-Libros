package AccentureLibros.dtos.ejemplarDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EjemplarRequestDTO(
        @NotBlank
        String localizacion,
        @NotNull
        Long usuarioId,
        @NotNull
        Long libroId
) {
}