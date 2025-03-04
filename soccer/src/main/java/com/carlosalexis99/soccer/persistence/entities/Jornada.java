package com.carlosalexis99.soccer.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jornadas")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero;

    //Llave foránea FK
    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    //Cuando generamos bidireccionalidad es porque la clase actual da su PK a la tabla (partidos) con el campo mapeado
    @OneToMany(mappedBy = "jornada")
    private List<Partido> partidos = new ArrayList<>();
}