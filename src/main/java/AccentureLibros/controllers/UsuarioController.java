package AccentureLibros.controllers;

import AccentureLibros.dtos.ApiResponseDTO;
import AccentureLibros.dtos.usuarioDTO.*;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    @Operation(summary = "Se crea un usuario")
    public ResponseEntity<UsuarioResponseDTO> createUsaurio(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        try{
            UsuarioResponseDTO usuarioResponseDTO = usuarioService.saveUsuario(usuarioRequestDTO);
            return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @GetMapping("/getAll")
    @Operation(summary = "Trae todos los usuarios de la base de datos")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> getAllUsuarios() {
        try {
            Set<UsuarioResponseDTO> usuarioResponseDTO = usuarioService.getAll();
            if (usuarioResponseDTO.isEmpty()) {
                return new ResponseEntity<>(new ApiResponseDTO<>(false, "No Hay Usuarios Registrados", usuarioResponseDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Usuarios Registrados", usuarioResponseDTO), HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza un usuario en particular")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> updateUsuario(@Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.update(usuarioUpdateDTO);
        String message = "Usuario Actualizado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, usuarioResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina un usuario en particular")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        String message = "Usuario Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/findUsuarioById/{id}")
    @Operation(summary = "Se Encuentra un Usuario por ID")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> findUsuarioById(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);
        String message = "Usuario encontrado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, usuarioResponseDTO), HttpStatus.OK);
    }
}