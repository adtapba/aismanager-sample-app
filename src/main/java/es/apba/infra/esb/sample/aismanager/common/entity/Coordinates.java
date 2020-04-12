package es.apba.infra.esb.sample.aismanager.common.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Class that represents geographical coordinates 
 * 
 * @author fsaucedo
 */
@Embeddable
@Data
public class Coordinates implements Serializable {

    private static final long serialVersionUID = 7778939770658158528L;
        
    @NotNull
    @Min(-90)
    @Max(90)
    private double latitude;
    
    @NotNull
    @Min(-90)
    @Max(90)
    private double longitude;
    
}
