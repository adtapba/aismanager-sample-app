package es.apba.infra.esb.sample.aismanager.geographicalline.boundary;

import es.apba.infra.esb.sample.aismanager.geographicalline.control.GeographicalLineNotFoundException;
import es.apba.infra.esb.sample.aismanager.geographicalline.control.GeographicalLineService;
import es.apba.infra.esb.sample.aismanager.geographicalline.entity.GeographicalLine;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
@Path("/geographicalline")
public class GeographicalLineResource {

    @Context
    UriInfo uriInfo;

    @Inject
    Logger logger;

    @Inject
    GeographicalLineService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        Response response;

        try {
            logger.log(Level.INFO, "Looking for geographical lines ...");

            List<GeographicalLine> entities = service.findAll();

            if (entities == null || entities.isEmpty()) {
                response = Response.status(Response.Status.NOT_FOUND).build();

                logger.log(Level.INFO, "NOT found any geographical lines");
            } else {
                response = Response.ok(entities).build();

                logger.log(Level.INFO, "{0} geographical lines found", entities.size());
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);

            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }
        
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(GeographicalLine input) {
        Response response;

        try {
            logger.log(Level.INFO, "Creating geographical line {0} ...", input);

            GeographicalLine output = service.create(input);

            response = Response.created(
                    getURI(output.getGeographicalLineCode()))
                    .build();

            logger.log(Level.INFO, "Geographical line created {0}", output);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);

            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }

        return response;
    }

    @GET
    @Path("{geographicalLineCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("geographicalLineCode") String geographicalLineCode) {
        Response response;

        try {
            logger.log(Level.INFO, "Looking for geographical line with code {0} ...", geographicalLineCode);

            GeographicalLine geographicalLine = service.findById(geographicalLineCode);

            if (geographicalLine != null) {
                response = Response.ok(geographicalLine).build();

                logger.log(Level.INFO, "Geographical line found {0}", geographicalLine);
            } else {
                response = Response.status(Response.Status.NOT_FOUND).build();

                logger.log(Level.INFO, "Geographical line with code {0} NOT found", geographicalLineCode);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);

            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }

        return response;
    }

    @PUT
    @Path("{geographicalLineCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("geographicalLineCode") String geographicalLineCode, GeographicalLine geographicalLine) {
        Response response;

        try {
            logger.log(Level.INFO, 
                    "Updating geographical line with code {0} and data {1} ...", 
                    new Object[] {geographicalLineCode, geographicalLine});

            geographicalLine.setGeographicalLineCode(geographicalLineCode);
            service.update(geographicalLine);

            response = Response.ok().build();

            logger.log(Level.INFO, "Geographical line with code {0} updated", geographicalLineCode);
        } catch (GeographicalLineNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);

            response = Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            
            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }

        return response;
    }

    @DELETE
    @Path("{geographicalLineCode}")
    public Response delete(@PathParam("geographicalLineCode") String geographicalLineCode) {
        Response response;

        try {
            logger.log(Level.INFO, "Deleting geographical line with code {0}...", geographicalLineCode);

            service.delete(geographicalLineCode);

            response = Response.ok().build();

            logger.log(Level.INFO, "Geographical line with code {0} deleted", geographicalLineCode);
        } catch (GeographicalLineNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);

            response = Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            
            response
                    = Response.serverError()
                            .entity(ex.getMessage())
                            .build();
        }

        return response;
    }

    private URI getURI(String geographicalLineCode) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(geographicalLineCode);

        return uriBuilder.build();
    }
    
}
