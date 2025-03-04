package com.carlosalexis99.soccer.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_final")
    private Date fechaFinal;

    @Column(name = "precio", precision = 6, scale = 2)
    private BigDecimal precio;

    //private String usuarioId;

}