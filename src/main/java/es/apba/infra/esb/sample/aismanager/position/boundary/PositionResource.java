package es.apba.infra.esb.sample.aismanager.position.boundary;

import es.apba.infra.esb.sample.aismanager.trackevent.control.TrackEventRepository;
import es.apba.infra.esb.sample.aismanager.trackevent.entity.TrackEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST resource for vessel AIS received positions
 *
 * @author fsaucedo
 */
@RequestScoped
@Path("/position")
public class PositionResource {

    @Inject
    Logger logger;

    @Inject
    TrackEventRepository repository;

    @GET
    @Path("/{vesselImoCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("vesselImoCode") long vesselImoCode) {
        Response response;

        try {
            logger.log(Level.INFO,
                    "Looking for the last position received for a vessel with imo code {0} ...",
                    vesselImoCode);

            TrackEvent lastEventReceivedForTheVessel = repository.findLastEventByVessel(vesselImoCode);

            response = Response.ok(lastEventReceivedForTheVessel.getCoordinates())
                    .build();

            logger.log(Level.INFO,
                    "Last position received for a vessel with imo code {0} is {1}",
                    new Object[]{vesselImoCode, lastEventReceivedForTheVessel.getCoordinates()});
        } catch (EJBException ex) {
            if (ex.getCausedByException() instanceof NoResultException) {
                response = Response.status(Response.Status.NOT_FOUND).build();

                logger.log(Level.INFO,
                        "NOT found any position for a vessel with imo code {0}",
                        vesselImoCode);
            } else {
                response = getServerError(ex);
            }

        } catch (Exception ex) {
            response = getServerError(ex);
        }

        return response;
    }

    private Response getServerError(Exception ex) {
        logger.log(Level.SEVERE, null, ex);

        return Response.serverError()
                .entity(ex.getMessage())
                .build();
    }

}
