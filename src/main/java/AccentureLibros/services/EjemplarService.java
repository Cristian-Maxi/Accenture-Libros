package AccentureLibros.services;

import AccentureLibros.dtos.ejemplarDTO.EjemplarRequestDTO;
import AccentureLibros.dtos.ejemplarDTO.EjemplarResponseDTO;

public interface EjemplarService {
    EjemplarResponseDTO saveEjemplar(EjemplarRequestDTO ejemplarRequestDTO);
    void delete(Long id);
}