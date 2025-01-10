package AccentureLibros.mappers;

import AccentureLibros.dtos.autorDTO.AutorResponseDTO;
import AccentureLibros.dtos.libroDTO.LibroRequestDTO;
import AccentureLibros.dtos.libroDTO.LibroResponseDTO;
import AccentureLibros.models.Autor;
import AccentureLibros.models.Libro;
import AccentureLibros.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LibroMapper {

    private static AutorRepository autorRepository;

    public LibroMapper(AutorRepository autorRepository) {
        LibroMapper.autorRepository = autorRepository;
    }

    public static Libro toEntity(LibroRequestDTO libroRequestDTO) {
        Autor autor = autorRepository.findById(libroRequestDTO.idAutor())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        return new Libro(
                libroRequestDTO.titulo(),
                libroRequestDTO.ISBN(),
                libroRequestDTO.editorial(),
                libroRequestDTO.paginas(),
                autor
        );
    }

    public static LibroResponseDTO toResponseDTO(Libro libro) {
        return new LibroResponseDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getISBN(),
                libro.getEditorial(),
                libro.getPaginas(),
                new AutorResponseDTO(
                        libro.getAutor().getId(),
                        libro.getAutor().getNombre()
                ));
    }

    public static Set<LibroResponseDTO> toResponseSetDTO(List<Libro> libros) {
        return libros.stream()
                .map(LibroMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }

}
