package AccentureLibros.dtos.libroDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LibroRequestDTO(
        @NotBlank(message = "El titulo no debe estar vacio")
        String titulo,
        @NotBlank(message = "El ISBN no debe estar vacio")
        String ISBN,
        @NotBlank(message = "El editorial no debe estar vacio")
        String editorial,
        @NotNull(message = "Debe ingresar un número de paginas")
        Integer paginas,
        @NotNull(message = "El idAutor no debe ser nulo")
        Long idAutor
) {
}
