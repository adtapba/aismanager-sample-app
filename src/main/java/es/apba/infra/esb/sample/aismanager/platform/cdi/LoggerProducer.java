package es.apba.infra.esb.sample.aismanager.platform.cdi;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Producer of loggers
 * 
 * @author fsaucedo
 */
@Dependent
public class LoggerProducer {
    
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    
}
