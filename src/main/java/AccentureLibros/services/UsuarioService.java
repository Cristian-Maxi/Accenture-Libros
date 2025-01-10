package AccentureLibros.services;


import AccentureLibros.dtos.usuarioDTO.*;

import java.util.Set;

public interface UsuarioService {
    UsuarioResponseDTO saveUsuario(UsuarioRequestDTO usuarioRequestDTO);
    Set<UsuarioResponseDTO> getAll();
    UsuarioResponseDTO update(UsuarioUpdateDTO usuarioUpdateDTO);
    void delete(Long id);
    UsuarioResponseDTO findById(Long id);
}