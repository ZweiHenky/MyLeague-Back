package com.carlosalexis99.soccer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String logo;

    //LLave foránea FK (Recibe de tablas usuarios)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
//    @JsonManagedReference("liga-equipo")
    private Usuario usuario;

    //Se genera tabla de unión
    @ManyToMany()
    @JoinTable(
            //@JoinTable: Especifica la tabla de unión
            //joinColumns: Indica qué columna de la tabla de unión hace referencia a la entidad actual
            //inverseJoinColumns: Indica qué columna de la tabla de unión hace referencia a la entidad relacionada
            //El nombre del atributo (jugadores) indica cómo estará mapeada la relación en Jugador
            name = "equipos_jugadores",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores = new HashSet<>();


    //Bidireccionalidad: Obtener información sobre los partidos del equipo
    @OneToMany(mappedBy = "equipoLocal")
    private Set<Partido> partidosComoLocal = new HashSet<>();

//    @ManyToMany(mappedBy = "equipoVisitante")
//    private Set<Partido> partidosComoVisitante = new HashSet<>();

    //InverseColumns: Perteneciente a tabla de unión
    @ManyToMany(mappedBy = "equipos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JsonBackReference("division-equipos")
    private List<Division> divisiones = new ArrayList<>();
}
