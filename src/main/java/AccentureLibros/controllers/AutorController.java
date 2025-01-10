package AccentureLibros.controllers;

import AccentureLibros.dtos.ApiResponseDTO;
import AccentureLibros.dtos.autorDTO.*;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.services.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/create")
    @Operation(summary = "Se crea un autor")
    public ResponseEntity<AutorResponseDTO> createAutor(@Valid @RequestBody AutorRequestDTO autorRequestDTO) {
        try{
            AutorResponseDTO autorResponseDTO = autorService.saveAutor(autorRequestDTO);
            return new ResponseEntity<>(autorResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @GetMapping("/getAll")
    @Operation(summary = "Trae todos los autores de la base de datos")
    public ResponseEntity<ApiResponseDTO<AutorResponseDTO>> getAllUsuarios() {
        try {
            Set<AutorResponseDTO> autorResponseDTO = autorService.getAll();
            if (autorResponseDTO.isEmpty()) {
                return new ResponseEntity<>(new ApiResponseDTO<>(false, "No Hay Autores Registrados", autorResponseDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Autores Registrados", autorResponseDTO), HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza un autor en particular")
    public ResponseEntity<ApiResponseDTO<AutorResponseDTO>> updateAutor(@Valid @RequestBody AutorUpdateDTO autorUpdateDTO) {
        AutorResponseDTO autorResponseDTO = autorService.update(autorUpdateDTO);
        String message = "Autor Actualizado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, autorResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina un autor en particular")
    public ResponseEntity<?> deleteAutor(@PathVariable Long id) {
        autorService.delete(id);
        String message = "Autor Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/findAutorById/{id}")
    @Operation(summary = "Se Encuentra un Autor por ID")
    public ResponseEntity<ApiResponseDTO<AutorResponseDTO>> findAutorById(@PathVariable Long id) {
        AutorResponseDTO autorResponseDTO = autorService.findById(id);
        String message = "Autor encontrado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, autorResponseDTO), HttpStatus.OK);
    }
}
