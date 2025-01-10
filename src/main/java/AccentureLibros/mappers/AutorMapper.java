package AccentureLibros.mappers;

import AccentureLibros.dtos.autorDTO.AutorRequestDTO;
import AccentureLibros.dtos.autorDTO.AutorResponseDTO;
import AccentureLibros.models.Autor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AutorMapper {
    public static Autor toEntity(AutorRequestDTO autorRequestDTO) {
        return new Autor(
                autorRequestDTO.nombre()
        );
    }

    public static AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(
                autor.getId(),
                autor.getNombre()
        );
    }

    public static Set<AutorResponseDTO> toResponseSetDTO(List<Autor> autores) {
        return autores.stream()
                .map(AutorMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }
}
