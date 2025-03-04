package com.carlosalexis99.soccer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;

    //Tablas a las que Usuario da su PK
    //Bidireccionalidad: Acceder a las ligas y equipos asociados al usuario
    //EAGER: Carga las ligas inmediatamente al cargar al usuario
    //LAZY: Carga DIFERIDA, pospone la carga de los datos hasta llamarlos por primera vez
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
//    @JsonBackReference("liga-usuario")
    private List<Liga> ligas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
//    @JsonBackReference("liga-equipo")
    private List<Equipo> equipos = new ArrayList<>();
}
