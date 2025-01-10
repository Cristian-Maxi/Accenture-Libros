package AccentureLibros.mappers;

import AccentureLibros.dtos.autorDTO.AutorResponseDTO;
import AccentureLibros.dtos.ejemplarDTO.EjemplarRequestDTO;
import AccentureLibros.dtos.ejemplarDTO.EjemplarResponseDTO;
import AccentureLibros.dtos.libroDTO.LibroResponseDTO;
import AccentureLibros.dtos.usuarioDTO.UsuarioResponseDTO;
import AccentureLibros.models.Ejemplar;
import AccentureLibros.models.Libro;
import AccentureLibros.models.Usuario;
import AccentureLibros.repositories.LibroRepository;
import AccentureLibros.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EjemplarMapper {

    @Autowired
    private static UsuarioRepository usuarioRepository;

    @Autowired
    private static LibroRepository libroRepository;

    public static EjemplarResponseDTO toResponseDTO(Ejemplar ejemplar) {
        return new EjemplarResponseDTO(
                ejemplar.getId(),
                ejemplar.getLocalizacion(),
                ejemplar.getFechaPrestacion(),
                ejemplar.getFechaDevolucion(),
                new UsuarioResponseDTO(
                        ejemplar.getUsuario().getId(),
                        ejemplar.getUsuario().getNombre()
                ),
                new LibroResponseDTO(
                        ejemplar.getLibro().getId(),
                        ejemplar.getLibro().getTitulo(),
                        ejemplar.getLibro().getISBN(),
                        ejemplar.getLibro().getEditorial(),
                        ejemplar.getLibro().getPaginas(),
                        new AutorResponseDTO(
                                ejemplar.getLibro().getAutor().getId(),
                                ejemplar.getLibro().getAutor().getNombre()
                        )
                )
        );
    }

    public static Ejemplar toEntity(EjemplarRequestDTO ejemplarRequestDTO) {
        Libro libro = libroRepository.findById(ejemplarRequestDTO.libroId())
                .orElseThrow(() -> new EntityNotFoundException("El id del libro no fue encontrado"));
        Usuario usuario = usuarioRepository.findById(ejemplarRequestDTO.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("El id del usuario no fue encontrado"));
        Ejemplar ejemplar = new Ejemplar(
                ejemplarRequestDTO.localizacion(),
                libro,
                usuario
        );
        ejemplar.setFechaPrestacion(LocalDate.now());
        ejemplar.setFechaDevolucion(LocalDate.now().plusMonths(3));
        return ejemplar;
    }
}
