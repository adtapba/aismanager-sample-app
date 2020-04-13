package es.apba.infra.esb.sample.aismanager.trackevent.control;

import es.apba.infra.esb.sample.aismanager.trackevent.entity.TrackEvent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository of track event entities
 * 
 * @author fsaucedo
 */
@Stateless
public class TrackEventRepository {
    
    @PersistenceContext
    EntityManager em;
    
    public TrackEvent findById(String id) {
        return em.find(TrackEvent.class, id);
    }
    
    public TrackEvent create(TrackEvent input) {
        return em.merge(input);
    }
    
}
