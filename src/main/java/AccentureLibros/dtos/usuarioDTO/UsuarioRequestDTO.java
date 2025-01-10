package AccentureLibros.dtos.usuarioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequestDTO(
        @NotBlank(message = "El nombre no debe estar vacio")
        String nombre,
        @NotNull @Pattern(regexp = "\\+?\\d{10,15}", message = "El número de teléfono debe ser válido")
        String telefono,
        @NotBlank (message = "La direccion no debe estar vacia")
        String direccion
) {
}