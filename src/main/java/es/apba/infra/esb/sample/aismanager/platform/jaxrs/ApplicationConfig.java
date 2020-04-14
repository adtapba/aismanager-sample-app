package es.apba.infra.esb.sample.aismanager.platform.jaxrs;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * JAX-RS config
 * 
 * @author fsaucedo
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(es.apba.infra.esb.sample.aismanager.geographicalline.boundary.GeographicalLineResource.class);
        resources.add(es.apba.infra.esb.sample.aismanager.position.boundary.PositionResource.class);
        resources.add(es.apba.infra.esb.sample.aismanager.trackevent.boundary.TrackEventResource.class);
    }
    
}
