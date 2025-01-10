package AccentureLibros.services.Impl;

import AccentureLibros.dtos.libroDTO.*;
import AccentureLibros.mappers.LibroMapper;
import AccentureLibros.models.Libro;
import AccentureLibros.repositories.LibroRepository;
import AccentureLibros.services.LibroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    @Transactional
    public LibroResponseDTO saveLibro(LibroRequestDTO libroRequestDTO) {
        Libro libro = LibroMapper.toEntity(libroRequestDTO);
        libroRepository.save(libro);
        return LibroMapper.toResponseDTO(libro);
    }

    @Override
    @Transactional
    public Set<LibroResponseDTO> getAll() {
        List<Libro> libros = libroRepository.findAll();
        return LibroMapper.toResponseSetDTO(libros);
    }

    @Override
    public LibroResponseDTO update(LibroUpdateDTO libroUpdateDTO) {
       Libro libro = libroRepository.findById(libroUpdateDTO.id())
               .orElseThrow(() -> new EntityNotFoundException("El ID del usuario no fue encontrado"));
       if(libroUpdateDTO.titulo() != null && !libroUpdateDTO.titulo().isBlank()) {
           libro.setTitulo(libroUpdateDTO.titulo());
       }
        if(libroUpdateDTO.ISBN() != null && !libroUpdateDTO.ISBN().isBlank()) {
            libro.setISBN(libroUpdateDTO.ISBN());
        }
        if(libroUpdateDTO.editorial() != null && !libroUpdateDTO.editorial().isBlank()) {
            libro.setEditorial(libroUpdateDTO.editorial());
        }
        if (libroUpdateDTO.paginas() != null && !libroUpdateDTO.paginas().isBlank()) {
            try {
                libro.setPaginas(Integer.parseInt(libroUpdateDTO.paginas().trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El campo 'paginas' debe ser un número entero o estar vacío.");
            }
        }
        libroRepository.save(libro);
        return LibroMapper.toResponseDTO(libro);
    }

    @Override
    public void delete(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        libroRepository.delete(libro);
    }

    @Override
    public LibroResponseDTO findById(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el ID del usuario ingresado"));
        return LibroMapper.toResponseDTO(libro);
    }
}
