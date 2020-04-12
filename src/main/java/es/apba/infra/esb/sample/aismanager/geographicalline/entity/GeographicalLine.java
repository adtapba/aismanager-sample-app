package es.apba.infra.esb.sample.aismanager.geographicalline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.apba.infra.esb.sample.aismanager.common.entity.Coordinates;
import es.apba.infra.esb.sample.aismanager.linecrossedevent.entity.LineCrossedEvent;
import java.io.Serializable;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

/**
 * Class that represents a geographical line to be controlled 
 * 
 * @author fsaucedo
 */

@Entity
@Data
public class GeographicalLine implements Serializable {
    
    private static final long serialVersionUID = -2668003108284227311L;
    
    @Id
    private String geographicalLineCode;
    
    private String description;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="longitude",
                           column=@Column(name="start_point_longitude")),
        @AttributeOverride(name="latitude",
                           column=@Column(name="start_point_latitude"))
    })
    private Coordinates startPoint;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="longitude",
                           column=@Column(name="end_point_longitude")),
        @AttributeOverride(name="latitude",
                           column=@Column(name="end_point_latitude"))
    })
    private Coordinates endPoint;
    
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "geographicalLine", cascade = CascadeType.REMOVE)
    private List<LineCrossedEvent> lineCrossedEvents;
    
}
