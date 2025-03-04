package com.carlosalexis99.soccer.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private Integer numero;

    @ManyToMany(mappedBy = "jugadores", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Equipo> equipos = new HashSet<>();

}