package es.apba.infra.esb.sample.aismanager.linecrossedevent.entity;

import es.apba.infra.esb.sample.aismanager.common.entity.VesselEvent;
import es.apba.infra.esb.sample.aismanager.geographicalline.entity.GeographicalLine;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that represents a line crossed by a vessel event
 * 
 * @author fsaucedo
 */
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LineCrossedEvent extends VesselEvent implements Serializable {

    private static final long serialVersionUID = 554652095663111055L;
    
    private final String uuid = UUID.randomUUID().toString();
    
    @Enumerated(EnumType.STRING)
    private LineCrossedEventType lineCrossedEventType;
    
    @ManyToOne
    private GeographicalLine geographicalLine;
    
}
