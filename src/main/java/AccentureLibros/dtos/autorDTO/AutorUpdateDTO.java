package AccentureLibros.dtos.autorDTO;

import jakarta.validation.constraints.NotNull;

public record AutorUpdateDTO(
        @NotNull(message = "El id no debe ser nulo")
        Long id,
        String nombre
) {
}
