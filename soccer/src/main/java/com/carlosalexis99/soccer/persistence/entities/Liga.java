package com.carlosalexis99.soccer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ligas")
public class Liga {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String direccion;
    private String logo;

    @OneToMany(mappedBy = "liga", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Division> division;

    //Llave foránea FK
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
//    @JsonManagedReference("liga-usuario")
    private Usuario usuario;

}