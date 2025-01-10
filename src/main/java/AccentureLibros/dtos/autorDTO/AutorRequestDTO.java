package AccentureLibros.dtos.autorDTO;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank(message = "El nombre no debe ser nulo")
        String nombre
) {
}
