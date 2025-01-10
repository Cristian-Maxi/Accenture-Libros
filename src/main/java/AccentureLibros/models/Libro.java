package AccentureLibros.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ISBN;
    private String editorial;
    private Integer paginas;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AUTOR_ID"))
    private Autor autor;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ejemplar> ejemplares;

    public Libro(String titulo, String ISBN, String editorial, int paginas, Autor autor) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.paginas = paginas;
        this.autor = autor;
    }
}