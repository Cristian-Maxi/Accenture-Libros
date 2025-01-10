package AccentureLibros.services;

import AccentureLibros.dtos.libroDTO.LibroRequestDTO;
import AccentureLibros.dtos.libroDTO.LibroResponseDTO;
import AccentureLibros.dtos.libroDTO.LibroUpdateDTO;

import java.util.Set;

public interface LibroService {
    LibroResponseDTO saveLibro(LibroRequestDTO libroRequestDTO);
    Set<LibroResponseDTO> getAll();
    LibroResponseDTO update(LibroUpdateDTO libroUpdateDTO);
    void delete(Long id);
    LibroResponseDTO findById(Long id);
}
