package AccentureLibros.dtos.libroDTO;

import jakarta.validation.constraints.NotNull;

public record LibroUpdateDTO(
        @NotNull(message = "El id no debe ser nulo")
        Long id,
        String titulo,
        String ISBN,
        String editorial,
        String paginas
) {
}