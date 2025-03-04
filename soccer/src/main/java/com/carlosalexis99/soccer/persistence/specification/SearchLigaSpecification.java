package com.carlosalexis99.soccer.persistence.specification;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchLigaSpecification implements Specification<Liga> {

    private String nombre;

    private BigDecimal minPremio;

    private BigDecimal maxPremio;


    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(nombre)){
            Expression<String> nombreLigaToLowerCase = criteriaBuilder.lower(root.get("nombre"));
            Predicate nombreLikePredicate = criteriaBuilder.like(nombreLigaToLowerCase, "%".concat(nombre.toLowerCase()).concat("%"));
            predicates.add(nombreLikePredicate);
        }

        if(minPremio != null && !minPremio.equals(BigDecimal.ZERO)){
            Predicate premioGreaterThanEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("premio"), minPremio);
            predicates.add(premioGreaterThanEqualPredicate);
        }

        if(maxPremio != null && !maxPremio.equals(BigDecimal.ZERO)){
            Predicate premioLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("premio"), maxPremio);
            predicates.add(premioLessThanEqualPredicate);
        }

//        query.orderBy(criteriaBuilder.asc(root.get("premio")));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
