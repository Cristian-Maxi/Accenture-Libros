package AccentureLibros.services.Impl;

import AccentureLibros.dtos.ejemplarDTO.EjemplarRequestDTO;
import AccentureLibros.dtos.ejemplarDTO.EjemplarResponseDTO;
import AccentureLibros.mappers.EjemplarMapper;
import AccentureLibros.models.Ejemplar;
import AccentureLibros.repositories.EjemplarRepository;
import AccentureLibros.services.EjemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EjemplarServiceImpl implements EjemplarService {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @Override
    @Transactional
    public EjemplarResponseDTO saveEjemplar(EjemplarRequestDTO ejemplarRequestDTO) {
        Ejemplar ejemplar = EjemplarMapper.toEntity(ejemplarRequestDTO);
        ejemplarRepository.save(ejemplar);
        return EjemplarMapper.toResponseDTO(ejemplar);
    }

    @Override
    public void delete(Long id) {
        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el ID ingresado"));
        ejemplarRepository.delete(ejemplar);
    }
}
