package AccentureLibros.services;


import AccentureLibros.dtos.autorDTO.AutorRequestDTO;
import AccentureLibros.dtos.autorDTO.AutorResponseDTO;
import AccentureLibros.dtos.autorDTO.AutorUpdateDTO;

import java.util.Set;

public interface AutorService {
    AutorResponseDTO saveAutor(AutorRequestDTO autorRequestDTO);
    Set<AutorResponseDTO> getAll();
    AutorResponseDTO update(AutorUpdateDTO autorUpdateDTO);
    void delete(Long id);
    AutorResponseDTO findById(Long id);
}
