package ejercicio16.Persona.repository;

import ejercicio16.Persona.domain.PersonaEntity;
import ejercicio16.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static ejercicio16.Persona.infrastructure.ControladorPersona.*;

public class PersonaRepoImpl {
    @PersistenceContext
    private EntityManager entityManager;


    public List<PersonaEntity> getData(HashMap<String, Object> conditions,Integer numPag,Integer tamPag) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaEntity> query = cb.createQuery(PersonaEntity.class);
        Root<PersonaEntity> root = query.from(PersonaEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) ->
        {
            switch (field) {
                case "orderCondition":
                    String orderCondition = (String) conditions.get("orderCondition");
                    switch (orderCondition) {
                        case NAME:
                            query.orderBy(cb.asc(root.get(NAME)));
                            break;
                        case USER:
                            query.orderBy(cb.asc(root.get(USER)));
                            break;
                    }
                    break;
                case "usuario":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    switch (dateCondition) {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(field), (Date) value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThanOrEqualTo(root.<Date>get(field), (Date) value));
                            break;
                    }
                    break;
            }

        });


        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        List<PersonaEntity> lista= entityManager.createQuery(query).getResultList();
        try{
        if(lista.size()<(numPag*tamPag)-1){
            return lista.subList((numPag-1)*tamPag, lista.size());
        }
        return lista.subList((numPag-1)*tamPag,(numPag*tamPag));
        }catch (Exception err){
            throw new NotFoundException("Numero de pagina");
        }
    }
}
