package es.apba.infra.esb.sample.aismanager.common.control;

import es.apba.infra.esb.sample.aismanager.common.entity.Vessel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository of vessel entities
 * 
 * @author fsaucedo
 */
@Stateless
public class VesselRepository {
    
    @PersistenceContext
    EntityManager em;
    
    public Vessel findById(Long imoCode) {
        return em.find(Vessel.class, imoCode);
    }
    
    public Vessel create(Vessel input) {
        return em.merge(input);
    }
    
    public Vessel update(Vessel input) throws VesselFoundException {
        Vessel entity = findById(input.getImoCode());

        if (entity != null) {
            entity.setName(input.getName());
            entity.setMmsi(input.getMmsi());
                    
            em.merge(entity);
            
            return entity;
        } else {
            throw new VesselFoundException();
        }
    }
    
}
