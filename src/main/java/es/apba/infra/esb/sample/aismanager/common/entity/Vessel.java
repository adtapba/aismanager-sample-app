package es.apba.infra.esb.sample.aismanager.common.entity;

import es.apba.infra.esb.sample.aismanager.linecrossedevent.entity.LineCrossedEvent;
import es.apba.infra.esb.sample.aismanager.trackevent.entity.TrackEvent;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

/**
 * Class that represents a vessel
 * 
 * @author fsaucedo
 */
@Entity
@Data
public class Vessel implements Serializable {
    
    private static final long serialVersionUID = 5288707446658466562L;
    
    @Id
    private long imoCode;
    
    private String name;
    
    private long mmsi;
        
    @ToString.Exclude
    @OneToMany(mappedBy = "vessel", cascade = CascadeType.REMOVE)
    private List<TrackEvent> trackEvents;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "vessel", cascade = CascadeType.REMOVE)
    private List<LineCrossedEvent> lineCrossedEvents;
    
}
