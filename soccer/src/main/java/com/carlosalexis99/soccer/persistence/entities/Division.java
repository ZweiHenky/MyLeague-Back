package com.carlosalexis99.soccer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="divisiones")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Integer edad_minima;
    private Integer edad_maxima;
    private BigDecimal premio;
    private BigDecimal arbitraje;

    //FK de ligas
    @ManyToOne
    @JoinColumn(name = "liga_id", nullable = false)
    private Liga liga;

    //Tabla de unión para llevar el control de los equipos por división
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "equipos_divisiones",
            joinColumns = @JoinColumn(name = "division_id"),
            inverseJoinColumns = @JoinColumn(name = "equipo_id")
    )
//    @JsonManagedReference("division-equipos")
    private List<Equipo> equipos = new ArrayList<>();
}
