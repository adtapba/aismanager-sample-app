package es.apba.infra.esb.sample.aismanager.trackevent.control;

import es.apba.infra.esb.sample.aismanager.common.control.VesselFoundException;
import es.apba.infra.esb.sample.aismanager.common.control.VesselRepository;
import es.apba.infra.esb.sample.aismanager.common.entity.Vessel;
import es.apba.infra.esb.sample.aismanager.notification.entity.Notification;
import es.apba.infra.esb.sample.aismanager.trackevent.entity.TrackEvent;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Service for managing track events
 *
 * @author fsaucedo
 */
@Stateless
public class TrackEventService {

    @Inject
    VesselRepository vesselRepository;

    @Inject
    TrackEventRepository trackEventRepository;

    @Inject
    Event<Notification> notification;

    public TrackEvent create(TrackEvent input) throws VesselFoundException {
        String id = input.getVessel().getMmsi() + "_" + input.getUuid(); // UUID from diferent stations may provoke collisions

        TrackEvent entity = trackEventRepository.findById(id);

        if (entity != null) {
            return entity; // Idempotency: a duplicated message  doesn't produce any change
        } else {
            Vessel vessel = vesselRepository.findById(input.getVessel().getImoCode());

            if (vessel == null) {
                vessel = vesselRepository.create(input.getVessel());
            } else {
                vessel = vesselRepository.update(input.getVessel());
            }
            input.setVessel(vessel);
            
            input.setId(id);
            
            return trackEventRepository.create(input);
        }
    }

}
