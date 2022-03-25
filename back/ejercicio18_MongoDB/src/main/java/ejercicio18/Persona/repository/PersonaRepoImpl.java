package ejercicio18.Persona.repository;

import com.sun.istack.NotNull;
import ejercicio18.Persona.domain.PersonaEntity;
import ejercicio18.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static ejercicio18.Persona.infrastructure.ControladorPersona.*;

@Repository
public class PersonaRepoImpl{
    @PersistenceContext
    private EntityManager entityManager;

    /*
    private final MongoTemplate mongoTemplate;
    @Autowired
    public PersonaRepoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<PersonaEntity> query(DynamicQuery dynamicQuery) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if(dynamicQuery.getId() != null) {
            criteria.add(Criteria.where("id").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
                    dynamicQuery.getId().toString(), Part.Type.CONTAINING
            ), "i"));
        }
        if(dynamicQuery.getPublishDateBefore() != null) {
            criteria.add(Criteria.where("publishDate").lte(dynamicQuery.getPublishDateBefore()));
        }
        if(dynamicQuery.getPublishDateAfter() != null) {
            criteria.add(Criteria.where("publishDate").gte(dynamicQuery.getPublishDateAfter()));
        }
        if(dynamicQuery.getSubject() != null) {
            criteria.add(Criteria.where("subjects").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
                    dynamicQuery.getSubject(), Part.Type.SIMPLE_PROPERTY
            ), "i"));
        }
        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Book.class);
    }
   /* @Autowired
    private JdbcOperations jdbc;

    @PersistenceContext
    private EntityManager entityManager;

    Integer id = 1;

    public List<PersonaEntity> findAll() {
        return jdbc.queryForObject(
                "SELECT * FROM persona",
                new personaListaRowMapper());
    }


    public PersonaEntity create(PersonaEntity entity) {
        entity.setId(id++);
        String sql = """
                INSERT INTO persona(id,usuario,password,name,
                surname,company_email,personal_email,city,active,created_date,
                imagen_url,termination_date)
                VALUES (?,?,?,?,?,?,?,?,?,?,?,?);
                """;
        jdbc.update(sql, entity.getId(), entity.getUsuario(), entity.getPassword(), entity.getName(), entity.getSurname(), entity.getCompany_email(), entity.getPersonal_email(), entity.getCity(), entity.getActive(), entity.getCreated_date(), entity.getImagen_url(), entity.getTermination_date());

        return entity;

    }

    public PersonaEntity update(PersonaEntity entity) {
        String sql = """
                UPDATE persona
                SET usuario=?,password=?,name=?,
                surname=?,company_email=?,personal_email=?,city=?,active=?,created_date=?,
                imagen_url=?,termination_date=?
                WHERE id=?;
                """;
        jdbc.update(sql, entity.getUsuario(), entity.getPassword(), entity.getName(), entity.getSurname(), entity.getCompany_email(), entity.getPersonal_email(), entity.getCity(), entity.getActive(), entity.getCreated_date(), entity.getImagen_url(), entity.getTermination_date(),entity.getId());
        return entity;
    }

    public Optional<PersonaEntity> findById(Integer id) {
        return jdbc.queryForObject(
                "SELECT * FROM persona WHERE id=?",
                new personaRowMapper(), id);
    }

    public List<PersonaEntity> findByUsuario(String usuario) {
        return jdbc.queryForObject(
                "SELECT * FROM persona WHERE usuario=?",
                new personaListaRowMapper(), usuario);
    }


    public void deleteById(Integer id) {
        jdbc.update(
                "DELETE FROM persona WHERE id=?;", id);
    }
*/

    public List<PersonaEntity> getData(@NotNull HashMap<String, Object> conditions, Integer numPag, Integer tamPag) {
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
        List<PersonaEntity> lista = entityManager.createQuery(query).getResultList();
        try {
            if (lista.size() < (numPag * tamPag) - 1) {
                return lista.subList((numPag - 1) * tamPag, lista.size());
            }
            return lista.subList((numPag - 1) * tamPag, (numPag * tamPag));
        } catch (Exception err) {
            throw new NotFoundException("Numero de pagina");
        }
    }
/*
    private class personaRowMapper implements RowMapper<Optional<PersonaEntity>> {

        @Override
        public Optional<PersonaEntity> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Optional.of(new PersonaEntity(rs));
        }
    }

    private class personaListaRowMapper implements RowMapper<List<PersonaEntity>> {

        @Override
        public List<PersonaEntity> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArrayList<PersonaEntity> listaUsuarios = new ArrayList();
            do {
                listaUsuarios.add(new PersonaEntity(rs));
            } while (rs.next());
            return listaUsuarios;
        }
    }
*/

}
