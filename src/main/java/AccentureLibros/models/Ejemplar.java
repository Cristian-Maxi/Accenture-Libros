package AccentureLibros.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String localizacion;
    private LocalDate fechaPrestacion;
    private LocalDate fechaDevolucion;
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false, foreignKey = @ForeignKey(name = "FK_LIBRO_ID"))
    private Libro libro;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_ID"))
    private Usuario usuario;

    public Ejemplar(String localizacion, Libro libro, Usuario usuario) {
        this.localizacion = localizacion;
        this.libro = libro;
        this.usuario = usuario;
    }
}