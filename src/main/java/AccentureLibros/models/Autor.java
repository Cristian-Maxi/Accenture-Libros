package AccentureLibros.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Libro> libros;

    public Autor(String nombre) {
        this.nombre = nombre;
    }
}