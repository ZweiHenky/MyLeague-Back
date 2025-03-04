package com.carlosalexis99.soccer.persistence.specification;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import com.carlosalexis99.soccer.persistence.entities.Liga;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchJugadorSpecification implements Specification<Jugador> {

    private String nombre;
    private Integer ligaId;

    @Override
    public Predicate toPredicate(Root<Jugador> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(nombre)){
            Expression<String> nombreToLower = criteriaBuilder.lower(root.get("nombre"));
            Predicate nombreLikePredicate = criteriaBuilder.like(nombreToLower, "%".concat(nombre.toLowerCase()).concat("%"));
            predicates.add(nombreLikePredicate);
        }

        if(ligaId != null){
            /*
            En este caso Jugador es la entidad de la que partimos, en esta escribimos la relación con una lista
            @ManyToMany(mappedBy = "jugadores", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            private Set<Equipo> equipos = new HashSet<>();
            de esta manera lo que va entre comillas es el nombre del atributo con el que indicamos la relación, es decir equipos
             */
            Join<Jugador, Equipo> equipos = root.join("equipos");
            Join<Equipo, Liga> ligas = equipos.join("ligas");
            Predicate ligaEqualPredicate = criteriaBuilder.equal(ligas.get("id"), ligaId);
            predicates.add(ligaEqualPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
