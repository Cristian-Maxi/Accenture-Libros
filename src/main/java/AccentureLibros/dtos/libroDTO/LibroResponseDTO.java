package AccentureLibros.dtos.libroDTO;

import AccentureLibros.dtos.autorDTO.AutorResponseDTO;

public record LibroResponseDTO(
        Long id,
        String titulo,
        String ISBN,
        String editorial,
        Integer paginas,
        AutorResponseDTO autorResponseDTO
) {
}
