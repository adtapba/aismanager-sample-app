package es.apba.infra.esb.sample.aismanager.trackevent.entity;

import es.apba.infra.esb.sample.aismanager.common.entity.VesselEvent;
import es.apba.infra.esb.sample.aismanager.common.entity.Coordinates;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that represents a vessel track event
 * 
 * @author fsaucedo
 */
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrackEvent extends VesselEvent implements Serializable {
    
    private static final long serialVersionUID = 4036137683091524280L;
    
    @Embedded
    private Coordinates coordinates;
    
}
