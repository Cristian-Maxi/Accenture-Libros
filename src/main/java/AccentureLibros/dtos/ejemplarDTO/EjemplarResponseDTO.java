package AccentureLibros.dtos.ejemplarDTO;

import AccentureLibros.dtos.libroDTO.LibroResponseDTO;
import AccentureLibros.dtos.usuarioDTO.UsuarioResponseDTO;

import java.time.LocalDate;

public record EjemplarResponseDTO(
        Long id,
        String localizacion,
        LocalDate fechaPrestacion,
        LocalDate fechaDevolucion,
        UsuarioResponseDTO usuarioResponseDTO,
        LibroResponseDTO libroResponseDTO
) {
}
