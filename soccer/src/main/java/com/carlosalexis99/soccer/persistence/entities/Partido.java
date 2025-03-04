package com.carlosalexis99.soccer.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;

    @Column(name = "gol_local")
    private Integer golesLocal;

    @Column(name = "gol_visitante")
    private Integer golesVisitante;

    //Llaves foráneas FK de la tabla equipos
    @ManyToOne
    @JoinColumn(name = "equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante")
    private Equipo equipoVisitante;

    //Llave foránea FK de la tabla jornadas
    @ManyToOne
    @JoinColumn(name = "jornada_id")
    private Jornada jornada;
}
