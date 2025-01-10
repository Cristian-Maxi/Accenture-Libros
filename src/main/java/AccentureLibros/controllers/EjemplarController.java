package AccentureLibros.controllers;

import AccentureLibros.dtos.ejemplarDTO.EjemplarRequestDTO;
import AccentureLibros.dtos.ejemplarDTO.EjemplarResponseDTO;
import AccentureLibros.exceptions.ApplicationException;
import AccentureLibros.services.EjemplarService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ejemplar")
public class EjemplarController {

    @Autowired
    private EjemplarService ejemplarService;

    @PostMapping("/create")
    @Operation(summary = "Este controlador se usa cuando un usuario saca un ejemplar",
            description = "Es necesario el ID del usuario y del ID del libro que quiere sacar")
    public ResponseEntity<EjemplarResponseDTO> createEjemplar(@Valid @RequestBody EjemplarRequestDTO ejemplarRequestDTO) {
        try{
            EjemplarResponseDTO ejemplarResponseDTO = ejemplarService.saveEjemplar(ejemplarRequestDTO);
            return new ResponseEntity<>(ejemplarResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Este controlador debe usarse cuando el usuario devuelve el libro que tomó")
    public ResponseEntity<?> deleteEjemplar(@PathVariable Long id) {
        ejemplarService.delete(id);
        String message = "Ejemplar Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
