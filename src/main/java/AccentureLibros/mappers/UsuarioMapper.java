package AccentureLibros.mappers;

import AccentureLibros.dtos.usuarioDTO.UsuarioRequestDTO;
import AccentureLibros.dtos.usuarioDTO.UsuarioResponseDTO;
import AccentureLibros.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO) {
        return new Usuario(
                usuarioRequestDTO.nombre(),
                usuarioRequestDTO.telefono(),
                usuarioRequestDTO.direccion()
        );
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre()
        );
    }

    public static Set<UsuarioResponseDTO> toResponseSetDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }
}