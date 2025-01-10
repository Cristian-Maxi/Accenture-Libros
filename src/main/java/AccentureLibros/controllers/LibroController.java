package AccentureLibros.controllers;

import AccentureLibros.dtos.ApiResponseDTO;
import AccentureLibros.dtos.libroDTO.*;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.services.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/create")
    @Operation(summary = "Se crea un libro")
    public ResponseEntity<LibroResponseDTO> createLibro(@Valid @RequestBody LibroRequestDTO libroRequestDTO) {
        try{
            LibroResponseDTO libroResponseDTO = libroService.saveLibro(libroRequestDTO);
            return new ResponseEntity<>(libroResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @GetMapping("/getAll")
    @Operation(summary = "Trae todos los libros de la base de datos")
    public ResponseEntity<ApiResponseDTO<LibroResponseDTO>> getAllLibros() {
        try {
            Set<LibroResponseDTO> libroResponseDTO = libroService.getAll();
            if (libroResponseDTO.isEmpty()) {
                return new ResponseEntity<>(new ApiResponseDTO<>(false, "No Hay Libros Registrados", libroResponseDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Libros Registrados", libroResponseDTO), HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza un autor en particular")
    public ResponseEntity<ApiResponseDTO<LibroResponseDTO>> updateLibro(@Valid @RequestBody LibroUpdateDTO libroUpdateDTO) {
        LibroResponseDTO libroResponseDTO = libroService.update(libroUpdateDTO);
        String message = "Libro Actualizado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, libroResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina un libro en particular")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id) {
        libroService.delete(id);
        String message = "Libro Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/findAutorById/{id}")
    @Operation(summary = "Se Encuentra un Libro por ID")
    public ResponseEntity<ApiResponseDTO<LibroResponseDTO>> findAutorById(@PathVariable Long id) {
        LibroResponseDTO libroResponseDTO = libroService.findById(id);
        String message = "Libro encontrado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, libroResponseDTO), HttpStatus.OK);
    }
}
