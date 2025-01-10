package AccentureLibros.services.Impl;

import AccentureLibros.dtos.autorDTO.*;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.mappers.AutorMapper;
import AccentureLibros.models.Autor;
import AccentureLibros.repositories.AutorRepository;
import AccentureLibros.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    @Transactional
    public AutorResponseDTO saveAutor(AutorRequestDTO autorRequestDTO) {
        if(autorRepository.existsByNombre(autorRequestDTO.nombre())) {
            throw new ApplicationException("nombre", "El nombre ya existe en la base de datos");
        }
        Autor autor = AutorMapper.toEntity(autorRequestDTO);
        autorRepository.save(autor);
        return AutorMapper.toResponseDTO(autor);
    }

    @Override
    @Transactional
    public Set<AutorResponseDTO> getAll() {
        List<Autor> autores = autorRepository.findAll();
        return AutorMapper.toResponseSetDTO(autores);
    }

    @Override
    public AutorResponseDTO update(AutorUpdateDTO autorUpdateDTO) {
        Autor autor = autorRepository.findById(autorUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("El ID del autor no fue encontrado"));
        if(autorUpdateDTO.nombre() != null && !autorUpdateDTO.nombre().isBlank()) {
            autor.setNombre(autorUpdateDTO.nombre());
        }
        return AutorMapper.toResponseDTO(autor);
    }

    @Override
    public void delete(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        autorRepository.delete(autor);
    }

    @Override
    public AutorResponseDTO findById(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El ID del autor no fue encontrado"));
        return AutorMapper.toResponseDTO(autor);
    }
}
