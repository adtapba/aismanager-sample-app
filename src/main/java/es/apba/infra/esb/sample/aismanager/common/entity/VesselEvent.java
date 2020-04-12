package es.apba.infra.esb.sample.aismanager.common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * Class that represents an abstract event related to a vessel
 * 
 * @author fsaucedo
 */
@MappedSuperclass
@Data
public abstract class VesselEvent implements Serializable {
    
    private static final long serialVersionUID = 2163870869464212946L;
    
    @Id
    private String id;
    
    @ManyToOne
    private Vessel vessel;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTimestamp;
    
}
