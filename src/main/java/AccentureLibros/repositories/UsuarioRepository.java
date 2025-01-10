package AccentureLibros.repositories;

import AccentureLibros.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByTelefono(String telefono);
}