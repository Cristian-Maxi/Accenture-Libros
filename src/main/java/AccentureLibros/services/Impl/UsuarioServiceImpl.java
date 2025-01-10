package AccentureLibros.services.Impl;

import AccentureLibros.dtos.usuarioDTO.*;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.mappers.UsuarioMapper;
import AccentureLibros.models.Usuario;
import AccentureLibros.repositories.UsuarioRepository;
import AccentureLibros.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if(usuarioRepository.existsByTelefono(usuarioRequestDTO.telefono())) {
            throw new ApplicationException("telefono", "El telefono ya existe en la base de datos");
        }
        Usuario usuario = UsuarioMapper.toEntity(usuarioRequestDTO);
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public Set<UsuarioResponseDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.toResponseSetDTO(usuarios);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("El ID del usuario no fue encontrado"));
        if(usuarioUpdateDTO.nombre() != null && !usuarioUpdateDTO.nombre().isBlank()) {
            usuario.setNombre(usuarioUpdateDTO.nombre());
        }
        if(usuarioUpdateDTO.telefono() != null && !usuarioUpdateDTO.telefono().isBlank()) {
            usuario.setTelefono(usuarioUpdateDTO.telefono());
        }
        if(usuarioUpdateDTO.direccion() != null && !usuarioUpdateDTO.direccion().isBlank()) {
            usuario.setDireccion(usuarioUpdateDTO.direccion());
        }
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El ID del usuario no fue encontrado"));
        return UsuarioMapper.toResponseDTO(usuario);
    }
}
