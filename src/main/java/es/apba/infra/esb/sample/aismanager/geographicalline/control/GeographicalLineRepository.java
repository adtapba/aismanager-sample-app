package es.apba.infra.esb.sample.aismanager.geographicalline.control;

import es.apba.infra.esb.sample.aismanager.geographicalline.entity.GeographicalLine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository of geographical line entities
 *
 * @author fsaucedo
 */
@Stateless
public class GeographicalLineRepository {

    @PersistenceContext
    EntityManager em;

    public List<GeographicalLine> findAll() {
        return em.createQuery(
                "select g "
                + "from GeographicalLine g")
                .getResultList();
    }

    public GeographicalLine findById(String geographicalLineCode) {
        return em.find(GeographicalLine.class, geographicalLineCode);
    }

    public GeographicalLine create(GeographicalLine input) {
        return em.merge(input);
    }

    public GeographicalLine update(GeographicalLine input) throws GeographicalLineNotFoundException {
        GeographicalLine entity = findById(input.getGeographicalLineCode());

        if (entity != null) {
            entity.setDescription(input.getDescription());
            entity.setStartPoint(input.getStartPoint());
            entity.setEndPoint(input.getEndPoint());
            em.merge(entity);
            
            return entity;
        } else {
            throw new GeographicalLineNotFoundException();
        }
    }

    public void delete(String geographicalLineCode) throws GeographicalLineNotFoundException {
        GeographicalLine entity = findById(geographicalLineCode);

        if (entity != null) {
            em.remove(entity);
        } else {
            throw new GeographicalLineNotFoundException();
        }
    }

}
