package es.apba.infra.esb.sample.aismanager.trackevent.boundary;

import es.apba.infra.esb.sample.aismanager.trackevent.control.TrackEventService;
import es.apba.infra.esb.sample.aismanager.trackevent.entity.TrackEvent;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author fsaucedo
 */
@RequestScoped
@Path("/trackevent")
public class TrackEventResource {

    @Context
    UriInfo uriInfo;

    @Inject
    Logger logger;

    @Inject
    TrackEventService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(TrackEvent input) {
        Response response;

        try {
            logger.log(Level.INFO, "Creating a track event {0} ...", input);

            TrackEvent output = service.create(input);

            response = Response.created(
                    getURI(output.getId()))
                    .build();

            logger.log(Level.INFO, "Track event created {0}", input.getUuid());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);

            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }

        return response;
    }

    private URI getURI(String uuid) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(uuid);

        return uriBuilder.build();
    }

}
