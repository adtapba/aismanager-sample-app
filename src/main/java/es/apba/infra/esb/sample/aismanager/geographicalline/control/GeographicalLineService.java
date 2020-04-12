package es.apba.infra.esb.sample.aismanager.geographicalline.control;

import es.apba.infra.esb.sample.aismanager.geographicalline.entity.GeographicalLine;
import es.apba.infra.esb.sample.aismanager.notification.entity.Notification;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Service for managing geographical lines
 *
 * @author fsaucedo
 */
@Stateless
public class GeographicalLineService {

    @Inject
    GeographicalLineRepository repository;

    @Inject
    private Event<Notification> notification;

    public List<GeographicalLine> findAll() {
        return repository.findAll();
    }

    public GeographicalLine findById(String geographicalLineCode) {
        return repository.findById(geographicalLineCode);
    }

    public GeographicalLine create(GeographicalLine input) {
        GeographicalLine output = repository.create(input);

        notification.fire(
                new Notification("Geographical line created with code "
                        + output.getGeographicalLineCode()));

        return output;
    }
    
    public GeographicalLine update(GeographicalLine input) throws GeographicalLineNotFoundException {
        GeographicalLine output = repository.update(input);

        notification.fire(
                new Notification("Geographical line with code "
                        + output.getGeographicalLineCode() + " updated"));

        return output;
    }
    
    public void delete(String geographicalLineCode) throws GeographicalLineNotFoundException {
        repository.delete(geographicalLineCode);

        notification.fire(
                new Notification("Geographical line with code "
                        + geographicalLineCode + " updated"));
    }

}
