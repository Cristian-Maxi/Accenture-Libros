package AccentureLibros.dtos.usuarioDTO;

import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDTO(
        @NotNull(message = "El id no debe ser nulo")
        Long id,
        String nombre,
        String telefono,
        String direccion
) {
}